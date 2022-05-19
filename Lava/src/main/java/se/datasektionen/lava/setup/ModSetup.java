package se.datasektionen.lava.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.entities.ShapeshifterEntity;

@Mod.EventBusSubscriber(modid = LavaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

	public static final String TAB_NAME = "lavamod";

	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Items.EMERALD);
		}
	};

	public static void init(FMLCommonSetupEvent event) {

	}
	
	@SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(Registration.SHAPESHIFTER.get(), ShapeshifterEntity.prepareAttributes().build());
    }

}
