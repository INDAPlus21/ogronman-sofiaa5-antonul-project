package se.datasektionen.lava.setup;

import static se.datasektionen.lava.LavaMod.MODID;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Registration {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
	private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);
	private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
	private static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MODID);

	public static void init() {

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		BLOCKS.register(bus);
		ITEMS.register(bus);
		BLOCK_ENTITIES.register(bus);
		CONTAINERS.register(bus);
		ENTITIES.register(bus);
		STRUCTURES.register(bus);
	}

	public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);

	public static final RegistryObject<Block> OBSIDIAN_INSCRIPTIONS = BLOCKS.register("obsidian_inscriptions", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(50f).explosionResistance(1200f)));
	public static final RegistryObject<Item> OBSIDIAN_INSCRIPTIONS_ITEM = fromBlock(OBSIDIAN_INSCRIPTIONS);

	public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
		return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
	}

}
