package com.lavamod.lava.datagen;

import com.lavamod.lava.LavaMod;
import com.lavamod.lava.setup.ModSetup;
import com.lavamod.lava.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;


public class TutLanguageProvider extends LanguageProvider{

	public TutLanguageProvider(DataGenerator gen, String locale) {
		super(gen, LavaMod.MODID, locale);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addTranslations() {
		add("itemGroup." + ModSetup.TAB_NAME, "Lava");
		
		
	}

}
