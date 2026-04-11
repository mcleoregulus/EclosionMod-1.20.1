package com.leoregulus.eclosion.block.custom;

import com.leoregulus.eclosion.block.ResearchTableBlockEntity;
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
import org.jetbrains.annotations.Nullable;

public class ResearchTableBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // 核心改动：使用 IS_DUMMY 区分。false为主方块（渲染大模型），true为假方块（隐形碰撞箱）
    public static final BooleanProperty IS_DUMMY = BooleanProperty.create("is_dummy");

    public ResearchTableBlock(Properties properties) {
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
            return new ResearchTableBlockEntity(pos, state);
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
}