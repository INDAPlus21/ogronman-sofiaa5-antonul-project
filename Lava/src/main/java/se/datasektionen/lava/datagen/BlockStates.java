package se.datasektionen.lava.datagen;

import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.blocks.FrameBlock;
import se.datasektionen.lava.blocks.utils.ModelProperty;
import se.datasektionen.lava.setup.Registration;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder.PartialBlockstate;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, LavaMod.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(Registration.OBSIDIAN_INSCRIPTIONS.get());
		// simpleBlock(Registration.FRAME_BLOCK.get());
		registerSlope();
		registerFrame();
		// dumbSpaghetti();
		// Dont really know how to do this when it isnt a simpleblock...
		// horizontalBlock(Registration.SLOPE_BLOCK.get(), null);
	}

	private void registerSlope() {

		// BlockModelBuilder slope = models().getBuilder("block/slope_block");
		Block block = Registration.SLOPE_BLOCK.get();
		ResourceLocation slope_back = modLoc("block/frame_block");
		ResourceLocation slope_side = modLoc("block/frame_slope_slide");
		horizontalBlock(block, models().orientableVertical(block.getRegistryName().getPath(), slope_side, slope_back));
	}

	private void dumbSpaghetti() {

		Object[] blocks = ForgeRegistries.BLOCKS.getEntries().toArray();

		for (int i = 0; i < blocks.length; i++) {
			String temp = blocks[i].toString();
			String index_model = temp.substring(temp.lastIndexOf(":") + 1, temp.length() - 1);
			String index_texture = "block/" + index_model;

			try {

				ResourceLocation texture = mcLoc(index_texture);
				ConfiguredModel model = new ConfiguredModel(models().withExistingParent(index_model, texture));
				System.out.println(index_model + ",");
			} catch (java.lang.IllegalStateException e) {
				try {

					ResourceLocation texture = modLoc(index_texture);
					ConfiguredModel model = new ConfiguredModel(models().withExistingParent(index_model, texture));
					System.out.println(index_model + ",");

				} catch (java.lang.Exception ex) {

				}
			}
		}
	}

	private void registerFrame() {
		Block block = Registration.FRAME_BLOCK.get();
		// VariantBlockStateBuilder frame = getVariantBuilder(block);

		// Object[] blocks = ForgeRegistries.BLOCKS.getEntries().toArray();

		PartialBlockstate partialBlockstate = null;
		int counter = 0;
	

		for (ModelProperty modelProperty : ModelProperty.values()) {
			counter++;
			// System.out.println(modelProperty);
			String index_block = "block/" + modelProperty.toString();

			if (partialBlockstate == null) {
				partialBlockstate = getVariantBuilder(block).partialState();
			} else {
				partialBlockstate = partialBlockstate.partialState();
			}

			ResourceLocation texture = null;
			if (counter < 686) {
				texture = mcLoc(index_block);
			} else {
				texture = modLoc(index_block);
			}
			//System.out.println(texture);
			UncheckedModelFile file = new UncheckedModelFile(texture);
			ConfiguredModel model = new ConfiguredModel(file);

			partialBlockstate = partialBlockstate.with(FrameBlock.TEXTURE, modelProperty).addModels(model);
		}
		
		partialBlockstate = partialBlockstate.partialState();
		String index_block = "block/frame_block";
		ResourceLocation texture = modLoc(index_block);
		ConfiguredModel model = new ConfiguredModel(models().cubeAll(index_block, texture));
		partialBlockstate = partialBlockstate.with(FrameBlock.TEXTURE, ModelProperty.frame_block).addModels(model);

	}

}
