package se.datasektionen.lava.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.blocks.FrameBlock;
import se.datasektionen.lava.blocks.SlopeBlock;
import se.datasektionen.lava.entities.ShapeshifterModel;
import se.datasektionen.lava.entities.ShapeshifterRenderer;

@Mod.EventBusSubscriber(modid = LavaMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	public static void init(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemBlockRenderTypes.setRenderLayer(Registration.FRAME_BLOCK.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(Registration.SLOPE_BLOCK.get(), RenderType.cutout());
		});
		FrameBlock.setupFrame();
		SlopeBlock.setupFrame();
	}
	
	@SubscribeEvent
	public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ShapeshifterModel.SHAPESHIFTER_LAYER, ShapeshifterModel::createBodyLayer);
	}
	
	@SubscribeEvent
	public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(Registration.SHAPESHIFTER.get(), ShapeshifterRenderer::new);
	}
	
	@SubscribeEvent
	public static void onTextureStitch(TextureStitchEvent.Pre event) {
		if(!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
			return;
		}
		
	}

	
	
}
