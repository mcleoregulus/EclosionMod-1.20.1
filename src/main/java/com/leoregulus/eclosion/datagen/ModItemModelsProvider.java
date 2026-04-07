package com.leoregulus.eclosion.datagen;

import com.leoregulus.eclosion.Eclosion;
import com.leoregulus.eclosion.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Eclosion.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ICE_ETHER.get());
        basicItem(ModItems.RAW_ICE_ETHER.get());
        basicItem(ModItems.INSPIRATION.get());
//        basicItem(ModItems.CARDBOARD.get());
    }
}
