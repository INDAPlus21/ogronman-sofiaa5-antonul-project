package se.datasektionen.lava.datagen;

import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class LavaBlockTags extends BlockTagsProvider {

	public LavaBlockTags(DataGenerator p_126511_, ExistingFileHelper existingFileHelper) {
		super(p_126511_, LavaMod.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addTags() {
		tag((Tag.Named<Block>) BlockTags.MINEABLE_WITH_AXE)
			.add(Registration.FRAME_BLOCK.get())
			.add(Registration.FRAME_SLOPE.get());
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(Registration.OBSIDIAN_INSCRIPTIONS.get());
		tag(BlockTags.NEEDS_STONE_TOOL)
			.add(Registration.FRAME_SLOPE.get());
		tag(BlockTags.NEEDS_DIAMOND_TOOL)
			.add(Registration.OBSIDIAN_INSCRIPTIONS.get());
		tag(BlockTags.DRAGON_IMMUNE)
			.add(Registration.OBSIDIAN_INSCRIPTIONS.get());

	}

	@Override
	public String getName() {
		return "LavaMod Tags";
	}
}
