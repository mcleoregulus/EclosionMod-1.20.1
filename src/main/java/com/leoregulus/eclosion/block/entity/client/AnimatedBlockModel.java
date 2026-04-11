package com.leoregulus.eclosion.block.entity.client;

import com.leoregulus.eclosion.Eclosion;
import com.leoregulus.eclosion.block.entity.AnimatedBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockModel extends GeoModel<AnimatedBlockEntity> {
    @Override
    public ResourceLocation getModelResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(Eclosion.MOD_ID, "geo/animated_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(Eclosion.MOD_ID, "textures/block/animated_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockEntity animatable) {
        return new ResourceLocation(Eclosion.MOD_ID, "animations/animated_block.animation.json");
    }
}
