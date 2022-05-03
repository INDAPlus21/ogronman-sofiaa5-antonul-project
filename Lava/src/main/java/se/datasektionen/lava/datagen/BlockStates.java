package se.datasektionen.lava.datagen;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder.PartialBlockstate;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.blocks.FrameBlock;
import se.datasektionen.lava.blocks.SlopeBlock;
import se.datasektionen.lava.blocks.utils.ModelProperty;
import se.datasektionen.lava.setup.Registration;

public class BlockStates extends BlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, LavaMod.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {

		simpleBlock(Registration.OBSIDIAN_INSCRIPTIONS.get());
		// simpleBlock(Registration.FRAME_BLOCK.get());
		registerFrame();
		// dumbSpaghetti();
		dumberSpaghetti();
		registerSlope();
		// Dont really know how to do this when it isnt a simpleblock...
		// horizontalBlock(Registration.SLOPE_BLOCK.get(), null);
	}

	private void registerSlope() {
		Block block = Registration.SLOPE_BLOCK.get();
		// VariantBlockStateBuilder frame = getVariantBuilder(block);

		// Object[] blocks = ForgeRegistries.BLOCKS.getEntries().toArray();

		PartialBlockstate partialBlockstate = null;
		int counter = 0;

		for (ModelProperty modelProperty : ModelProperty.values()) {
			
			
			counter++;
			
			if(modelProperty.toString().equals("slope_block")) {
				continue;
			}
			
			// System.out.println(modelProperty);
			String blockName =  modelProperty.toString()+"_slope";
			String index_block = "block/" + blockName;
			

			if (partialBlockstate == null) {
				partialBlockstate = getVariantBuilder(block).partialState();
			} else {
				partialBlockstate = partialBlockstate.partialState();
			}

			ResourceLocation texture = null;

			texture = modLoc(index_block);
			
			// System.out.println(texture);
			UncheckedModelFile file = new UncheckedModelFile(texture);
			ConfiguredModel model = new ConfiguredModel(file);
			int dir_value = 0;
	        for (Direction facing : Direction.Plane.HORIZONTAL) {
	            if (partialBlockstate == null) {
	                partialBlockstate = getVariantBuilder(block).partialState();
	            } else {
	                partialBlockstate = partialBlockstate.partialState();
	            }
	            
	            ConfiguredModel[] new_models = ConfiguredModel.builder().modelFile(file).rotationY(dir_value).build();
				partialBlockstate = partialBlockstate.with(SlopeBlock.FACING, facing).with(FrameBlock.TEXTURE, modelProperty).addModels(new_models);
				partialBlockstate = partialBlockstate.partialState();
				dir_value += 90;
	        }
		}
		
		partialBlockstate = partialBlockstate.partialState();
		ResourceLocation slope_back = modLoc("block/frame_block");
		ResourceLocation slope_side = modLoc("block/frame_slope_slide");
		String index_block = "block/slope_block";
		ResourceLocation texture = modLoc(index_block);
		UncheckedModelFile file = new UncheckedModelFile(texture);

		int dir_value = 0;
        for (Direction facing : Direction.Plane.HORIZONTAL) {
            if (partialBlockstate == null) {
                partialBlockstate = getVariantBuilder(block).partialState();
            } else {
                partialBlockstate = partialBlockstate.partialState();
            }
            
            ConfiguredModel[] new_models = ConfiguredModel.builder().modelFile(file).rotationY(dir_value).build();
			partialBlockstate = partialBlockstate.with(SlopeBlock.FACING, facing).with(FrameBlock.TEXTURE, ModelProperty.slope_block).addModels(new_models);
			partialBlockstate = partialBlockstate.partialState();
			dir_value += 90;
        }
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

	private void dumberSpaghetti() {
		File dummy_file = new File("DummyDumb");
		String absolutPath = "";
		absolutPath = dummy_file.getAbsolutePath();
		System.out.println("Getting path...");
		absolutPath = absolutPath.substring(0, absolutPath.lastIndexOf("run")) + "src\\main\\resources\\assets\\lava\\";
		String path_model = absolutPath + "models\\block";
		File file = new File(path_model + "\\slope_block.json");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String content = "";
		while (sc.hasNextLine()) {
			content += sc.nextLine() + "\n";
		}
		sc.close();

		int iter = 0;

		for (ModelProperty modelProperty : ModelProperty.values()) {
			iter++;

			String new_content = "";

			String name = modelProperty.toString();

			if (name.contains("wood")) {
				name = name.replace("wood", "log");
			}

			if (name.contains("_slab")) {
				name = name.replace("_slab", "");
			}

			if (name.contains("_stairs")) {
				name = name.replace("_stairs", "");
			}

			if (name.contains("_pressure_plate")) {
				name = name.replace("_pressure_plate", "");
			}

			String replace_content_texture = "";
			String replace_content_slope = "";

			if (iter < 634-3) {
				replace_content_texture = "minecraft:block/" + modelProperty.toString();
				replace_content_slope = "lava:block/" + modelProperty.toString() + "_slope";
			} else {
				replace_content_texture = "lava:block/" + modelProperty.toString();
				replace_content_slope = "lava:block/" + modelProperty.toString() + "_slope";
			}

			new_content = content.replace("lava:block/frame_block", replace_content_texture)
					.replace("lava:block/frame_slope_slide", replace_content_slope);

			File newF = new File(path_model + "\\" + modelProperty.toString() + "_slope.json");

			try {
				FileWriter myWriter = new FileWriter(newF);
				myWriter.write(new_content);
				myWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String texture_path = absolutPath + "\\textures\\block\\obsidian_inscriptions.png";
			texture_path = "../../../../../../../../src/main/resources/assets/obsidian_inscriptions.png";

			String base = "C:\\Users\\ozkar\\Documents\\git\\ogronman-sofiaa5-antonul-project\\Lava\\build\\classes\\java\\main\\se\\datasektionen\\lava\\datagen";
			String path = "C:\\Users\\ozkar\\Documents\\git\\ogronman-sofiaa5-antonul-project\\Lava\\src\\test\\resources\\assets\\lava\\textures\\block\\";

			String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();

			relative += name + ".png";
			String helper_path = absolutPath + "\\textures\\block\\helper.png";
			helper_path = new File(base).toURI().relativize(new File(helper_path).toURI()).getPath();
			BufferedImage slope_texture = null;
			BufferedImage output_image = null;
			BufferedImage middleImage = null;
			try {
				slope_texture = ImageIO.read(getClass().getResourceAsStream(relative));
				// output_image = ImageIO.read(getClass().getResourceAsStream(helper_path));
				
				output_image = new BufferedImage(slope_texture.getWidth() * 43, slope_texture.getHeight() * 43,
						BufferedImage.TYPE_INT_ARGB);
				
				middleImage = new BufferedImage(output_image.getWidth(), output_image.getHeight(), BufferedImage.TYPE_INT_ARGB);
				
				String output_path = absolutPath + "\\textures\\block\\" + name + "_slope.png";
				
				
		        Graphics g2d = middleImage.createGraphics();
		        g2d.drawImage(slope_texture, 0, 0, middleImage.getWidth(), middleImage.getHeight(), null);
		        g2d.dispose();
		        
				File output = new File(output_path);

				int offset = 0;

				Graphics g = output_image.createGraphics();
				g.setColor(new Color(0, 0, 0, 0));
				g.fillRect(0, 0, output_image.getWidth(), output_image.getHeight());

				for (int x = 0; x < middleImage.getWidth(); x++) {
					for (int y = offset; y < middleImage.getHeight() - offset; y++) {
						int color = middleImage.getRGB(x, y);
						output_image.setRGB(x, y, color);
					}
					offset++;
				}

				ImageIO.write(output_image, "png", output);

			} catch (Exception e) {

				try {

					relative = relative.substring(0, relative.lastIndexOf(".png"));
					relative += "_side.png";

					slope_texture = ImageIO.read(getClass().getResourceAsStream(relative));
					// output_image = ImageIO.read(getClass().getResourceAsStream(helper_path));
					output_image = new BufferedImage(slope_texture.getWidth() * 43, slope_texture.getHeight() * 43,
							BufferedImage.TYPE_INT_ARGB);
					String output_path = absolutPath + "\\textures\\block\\" + modelProperty.toString() + "_slope.png";

					File output = new File(output_path);

					int offset = 0;

					Graphics g = output_image.createGraphics();
					g.setColor(new Color(0, 0, 0, 0));
					g.fillRect(0, 0, output_image.getWidth(), output_image.getHeight());

					for (int x = 0; x < middleImage.getWidth(); x++) {
						for (int y = offset; y < middleImage.getHeight() - offset ; y++) {
							int color = middleImage.getRGB(x , y);
							output_image.setRGB(x, y, color);
						}
						offset++;
					}

					ImageIO.write(output_image, "png", output);
				} catch (Exception ex) {
					System.out.println("Could not find file for: " + modelProperty.toString());
					System.out.println(e.getMessage());
					System.out.println(ex.getMessage());
				}

			}
		}
		System.out.println("Files are done");

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
			if (counter < 634-3) {
				texture = mcLoc(index_block);
			} else {
				texture = modLoc(index_block);
			}
			// System.out.println(texture);
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
