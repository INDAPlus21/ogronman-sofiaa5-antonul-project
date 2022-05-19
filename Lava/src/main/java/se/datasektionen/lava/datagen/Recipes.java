package se.datasektionen.lava.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import se.datasektionen.lava.setup.Registration;

public class Recipes extends RecipeProvider {

	public Recipes(DataGenerator p_125973_) {
		super(p_125973_);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(Registration.FRAME_BLOCK.get())
			.pattern("x x")
			.pattern(" x ")
			.pattern("x x")
			.define('x', Tags.Items.RODS_WOODEN)
			.group("lava")
			.unlockedBy("framing", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.FRAME_BLOCK.get()))
			.save(consumer);
	}

}
