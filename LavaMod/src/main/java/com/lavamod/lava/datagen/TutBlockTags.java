package com.lavamod.lava.datagen;



import com.lavamod.lava.LavaMod;
import com.lavamod.lava.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockTags extends BlockTagsProvider{

	public TutBlockTags(DataGenerator p_126511_, ExistingFileHelper existingFileHelper) {
		super(p_126511_, LavaMod.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void addTags() {

	}
	
	@Override
	public String getName() { return "Example Tags";}
}
