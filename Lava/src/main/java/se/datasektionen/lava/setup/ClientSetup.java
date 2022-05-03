package se.datasektionen.lava.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import se.datasektionen.lava.blocks.FrameBlock;
import se.datasektionen.lava.blocks.SlopeBlock;

public class ClientSetup {

	public static void init(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemBlockRenderTypes.setRenderLayer(Registration.FRAME_BLOCK.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(Registration.SLOPE_BLOCK.get(), RenderType.cutout());
		});
		FrameBlock.setupFrame();
		SlopeBlock.setupFrame();
	}

}
