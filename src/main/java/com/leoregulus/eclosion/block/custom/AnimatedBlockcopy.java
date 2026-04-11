package com.leoregulus.eclosion.block.custom;

import com.leoregulus.eclosion.block.entity.AnimatedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AnimatedBlockcopy extends BaseEntityBlock {
    public AnimatedBlockcopy(Properties properties) {
        super(properties);
    }

//    @Override
//    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
//        level.playSound(player, blockPos, ModSounds.COOL_SOUND.get(), SoundSource.BLOCKS, 1f, 1f);
//        return super.use(blockState, level, blockPos, player, hand, hitResult);
//    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState state) {
        return new AnimatedBlockEntity(blockPos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
