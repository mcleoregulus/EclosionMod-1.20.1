package com.leoregulus.eclosion.datagen;

import com.leoregulus.eclosion.Eclosion;
import com.leoregulus.eclosion.block.ModBlocks;
import com.leoregulus.eclosion.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, Eclosion.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(ModItems.ICE_ETHER.get(), "Ice Ether");
        add(ModItems.RAW_ICE_ETHER.get(), "Raw Ice Ether");
        add(ModItems.CARDBOARD.get(), "Cardboard");
        add(ModItems.INSPIRATION.get(), "Inspiration");

        add(ModBlocks.ICE_ETHER_BLOCK.get(), "Ice Ether Block");
        add(ModBlocks.RAW_ICE_ETHER_BLOCK.get(), "Raw Ice Ether Block");
        add(ModBlocks.ICE_ETHER_ORE.get(), "Ice Ether Ore");

        add("itemGroup.eclosion", "Eclosion");
    }
}
