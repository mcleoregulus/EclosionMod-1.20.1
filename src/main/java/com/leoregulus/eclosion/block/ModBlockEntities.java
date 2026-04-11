package com.leoregulus.eclosion.block;

import com.leoregulus.eclosion.Eclosion;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.example.block.entity.FertilizerBlockEntity;
import software.bernie.example.registry.BlockRegistry;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Eclosion.MOD_ID);

    public static final RegistryObject<BlockEntityType<ResearchTableBlockEntity>> RESEARCH_TABLE_BE =
            BLOCK_ENTITIES.register("research_table_be", () ->
                    BlockEntityType.Builder.of(ResearchTableBlockEntity::new,
                            ModBlocks.RESEARCH_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<FertilizerBlockEntity>> FERTILIZER_BLOCK =
            BLOCK_ENTITIES.register("fertilizer", () -> BlockEntityType.Builder
                    .of(FertilizerBlockEntity::new, BlockRegistry.FERTILIZER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}