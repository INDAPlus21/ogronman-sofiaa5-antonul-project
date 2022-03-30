package com.lavamod.lava.datagen;

import org.jetbrains.annotations.Nullable;

import com.lavamod.lava.LavaMod;
import com.lavamod.lava.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemTags extends ItemTagsProvider{

	public TutItemTags(DataGenerator p_126530_, BlockTagsProvider p_126531_,@Nullable ExistingFileHelper existingFileHelper) {
		super(p_126530_, p_126531_, LavaMod.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void addTags() {

	}
	
	@Override
	public String getName() { return "Example Tags";}
}
