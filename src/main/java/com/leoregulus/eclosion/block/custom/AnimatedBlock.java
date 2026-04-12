package com.leoregulus.eclosion.block.custom;

import com.leoregulus.eclosion.block.entity.AnimatedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class AnimatedBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // 核心改动：使用 IS_DUMMY 区分。false为主方块（渲染大模型），true为假方块（隐形碰撞箱）
    public static final BooleanProperty IS_DUMMY = BooleanProperty.create("is_dummy");

    public AnimatedBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(IS_DUMMY, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, IS_DUMMY);
    }

    // ========== 渲染控制 ==========
    @Override
    public RenderShape getRenderShape(BlockState state) {
        if (state.getValue(IS_DUMMY)) {
            return RenderShape.INVISIBLE;
        }
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
    // ========== 实体控制 ==========
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        if (!state.getValue(IS_DUMMY)) {
            // 只有主方块拥有 BlockEntity，现在返回真实的对象！
            return new AnimatedBlockEntity(pos, state);
        }
        return null;
    }

    // ========== 放置逻辑 ==========
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        BlockPos pos = context.getClickedPos();
        // 假方块（底座）放在玩家视角的逆时针方向（也就是左边/右边，根据实际模型可能需要调整）
        BlockPos rightPos = pos.relative(facing.getCounterClockWise());

        // 检查旁边那格能不能放东西
        if (context.getLevel().getBlockState(rightPos).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, facing).setValue(IS_DUMMY, false);
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            Direction facing = state.getValue(FACING);
            BlockPos rightPos = pos.relative(facing.getCounterClockWise());
            // 自动在旁边放一个隐形的假方块
            level.setBlock(rightPos, state.setValue(IS_DUMMY, true), 3);
        }
    }

    // ========== 连锁破坏逻辑 ==========
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        Direction facing = state.getValue(FACING);
        boolean isDummy = state.getValue(IS_DUMMY);

        // 主方块在找逆时针的假方块兄弟，假方块在找顺时针的主方块兄弟
        Direction expectedNeighborDir = isDummy ? facing.getClockWise() : facing.getCounterClockWise();

        if (direction == expectedNeighborDir && !neighborState.is(this)) {
            // 兄弟被挖了，自己也碎掉
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        // 确保打开 GUI 的逻辑只在服务器端运行
        if (!level.isClientSide) {
            BlockPos mainPos = pos;
            boolean isDummy = state.getValue(IS_DUMMY);

            // 如果玩家右击的是假方块（透明碰撞箱），我们需要定位回主方块
            if (isDummy) {
                Direction facing = state.getValue(FACING);
                // 在你的设定中，假方块放在逆时针方向，所以主方块在假方块的顺时针方向
                mainPos = pos.relative(facing.getClockWise());
            }

            // 获取主方块位置上的 BlockEntity
            BlockEntity blockEntity = level.getBlockEntity(mainPos);

            // 检查这个 BlockEntity 是否实现了 MenuProvider 接口（也就是是否绑定了菜单）
            if (blockEntity instanceof MenuProvider) {
                // 原版标准写法：为玩家打开对应的菜单
                // player.openMenu((MenuProvider) blockEntity);

                // Forge 推荐写法：支持在打开网络发包时传递额外的位置数据（推荐使用这个）
                NetworkHooks.openScreen((ServerPlayer) player, (MenuProvider) blockEntity, mainPos);
            }
        }

        // 返回 SUCCESS 表示玩家已经成功和方块交互了，这样玩家就不会把手里的方块（比如火把）放在这个方块上
        return InteractionResult.SUCCESS;
    }

}