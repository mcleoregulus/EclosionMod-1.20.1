package com.leoregulus.eclosion.tag;

import com.leoregulus.eclosion.Eclosion;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> ORE_TAGS = create("ore_tags");
    public static final TagKey<Block> PICKAXE_AXE_MINEABLE = createForgeTag("tools/pickaxe_axe_mineable");

    private static TagKey<Block> create(String pName) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Eclosion.MOD_ID, pName));
    }

    private static TagKey<Block> createForgeTag(String pName) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("forge", pName));
    }
}
