package com.leoregulus.eclosion.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

// 实现 GeoBlockEntity 接口
public class ResearchTableBlockEntity extends BlockEntity implements GeoBlockEntity {
    // GeckoLib 要求的动画缓存器
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ResearchTableBlockEntity(BlockPos pos, BlockState state) {
        // 绑定到我们在第一步注册的类型上
        super(ModBlockEntities.RESEARCH_TABLE_BE.get(), pos, state);
    }

    // 在这里注册你的动画控制器（比如翻书动画），我们暂时留空
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
