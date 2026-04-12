package com.leoregulus.eclosion.datagen;

import com.leoregulus.eclosion.Eclosion;
import com.leoregulus.eclosion.block.ModBlocks;
import com.leoregulus.eclosion.tag.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Eclosion.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ICE_ETHER_BLOCK.get())
                .add(ModBlocks.ICE_ETHER_ORE.get());
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.ANIMATED_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ICE_ETHER_ORE.get());

        tag(ModBlockTags.ORE_TAGS)
                .add(ModBlocks.ICE_ETHER_ORE.get())
                .addTag(BlockTags.COAL_ORES)
                .addTag(BlockTags.IRON_ORES)
                .addTag(BlockTags.GOLD_ORES)
                .addTag(BlockTags.COPPER_ORES)
                .addTag(BlockTags.DIAMOND_ORES)
                .addTag(BlockTags.EMERALD_ORES)
                .addTag(BlockTags.LAPIS_ORES)
                .addTag(BlockTags.REDSTONE_ORES);

        tag(BlockTags.FENCES)
                .add(ModBlocks.ICE_ETHER_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.ICE_ETHER_FENCE_GATE.get());
        tag(BlockTags.WALLS)
                .add(ModBlocks.ICE_ETHER_WALL.get());

        tag(ModBlockTags.PICKAXE_AXE_MINEABLE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE);
    }
}
