package com.leoregulus.eclosion;

import com.leoregulus.eclosion.block.ModBlockEntities;
import com.leoregulus.eclosion.client.renderer.block.FertilizerBlockRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Eclosion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientListener {
	@SubscribeEvent
	public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {

		event.registerBlockEntityRenderer(ModBlockEntities.FERTILIZER_BLOCK.get(), context -> new FertilizerBlockRenderer());

	}
}
