package com.leoregulus.eclosion.item.client;

import com.leoregulus.eclosion.Eclosion;
import com.leoregulus.eclosion.item.custom.AnimatedBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AnimatedBlockItemModel extends GeoModel<AnimatedBlockItem> {
    @Override
    public ResourceLocation getModelResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(Eclosion.MOD_ID, "geo/animated_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(Eclosion.MOD_ID, "textures/block/animated_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnimatedBlockItem animatable) {
        return new ResourceLocation(Eclosion.MOD_ID, "animations/animated_block.animation.json");
    }
}
