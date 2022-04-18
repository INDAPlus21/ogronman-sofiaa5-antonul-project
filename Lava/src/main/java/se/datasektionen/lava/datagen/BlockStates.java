package se.datasektionen.lava.datagen;

import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;

import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, LavaMod.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {

		simpleBlock(Registration.OBSIDIAN_INSCRIPTIONS.get());
		simpleBlock(Registration.FRAME_BLOCK.get());
		//Dont really know how to do this when it isnt a simpleblock...
		//horizontalBlock(Registration.SLOPE_BLOCK.get(), null);
	}

}
