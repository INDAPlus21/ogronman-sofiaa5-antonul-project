package com.lavamod.lava.datagen;


import com.lavamod.lava.LavaMod;
import com.lavamod.lava.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;

import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider{
	
	public TutBlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, LavaMod.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
	}

}
