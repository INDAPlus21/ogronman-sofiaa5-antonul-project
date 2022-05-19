package se.datasektionen.lava.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import se.datasektionen.lava.blocks.FrameBlock;
import se.datasektionen.lava.blocks.FrameStairs;

public class ClientSetup {

	public static void init(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemBlockRenderTypes.setRenderLayer(Registration.FRAME_BLOCK.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(Registration.FRAME_SLOPE.get(), RenderType.cutout());
		});
		FrameBlock.setupFrame();
		FrameStairs.setupFrame(FrameBlock.eligibleBlocks);
	}

}
