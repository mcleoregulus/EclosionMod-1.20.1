package com.leoregulus.eclosion.datagen;

import com.leoregulus.eclosion.Eclosion;
import com.leoregulus.eclosion.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Eclosion.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.ICE_ETHER_BLOCK.get(), cubeAll(ModBlocks.ICE_ETHER_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.RAW_ICE_ETHER_BLOCK.get(), cubeAll(ModBlocks.RAW_ICE_ETHER_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.ICE_ETHER_ORE.get(), cubeAll(ModBlocks.ICE_ETHER_ORE.get()));
    }
}
