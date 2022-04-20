package se.datasektionen.lava.datagen;

import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, LavaMod.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {

		simpleBlock(Registration.OBSIDIAN_INSCRIPTIONS.get());
		simpleBlock(Registration.FRAME_BLOCK.get());
		registerSlope();
		//Dont really know how to do this when it isnt a simpleblock...
		//horizontalBlock(Registration.SLOPE_BLOCK.get(), null);
	}
	
	
	
	
	private void registerSlope() {
		
		//BlockModelBuilder slope = models().getBuilder("block/slope_block");
		Block block = Registration.SLOPE_BLOCK.get();
		ResourceLocation slope_back = modLoc("block/frame_block");
		ResourceLocation slope_side = modLoc("block/frame_slope_slide");
		horizontalBlock(block, models().orientableVertical(block.getRegistryName().getPath(), slope_side, slope_back));
	}


}
