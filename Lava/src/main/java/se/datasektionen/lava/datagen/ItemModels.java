package se.datasektionen.lava.datagen;

import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, LavaMod.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerModels() {
		withExistingParent(Registration.OBSIDIAN_INSCRIPTIONS_ITEM.get().getRegistryName().getPath(),
				modLoc("block/obsidian_inscriptions"));
		withExistingParent(Registration.FRAME_BLOCK_ITEM.get().getRegistryName().getPath(),
				modLoc("block/frame_block"));
		withExistingParent(Registration.FRAME_SLOPE_ITEM.get().getRegistryName().getPath(),
				modLoc("block/frame_slope"));
	}

}
