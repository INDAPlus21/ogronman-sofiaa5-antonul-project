package se.datasektionen.lava.setup;

import static se.datasektionen.lava.LavaMod.MODID;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
import se.datasektionen.lava.blocks.FrameBlock;
import se.datasektionen.lava.blocks.FrameSlope;
import se.datasektionen.lava.blocks.FrameStairs;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Registration {

	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
		.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
	private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
		MODID);
	private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
		MODID);
	private static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister
		.create(ForgeRegistries.STRUCTURE_FEATURES, MODID);

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

	public static final RegistryObject<Block> OBSIDIAN_INSCRIPTIONS = BLOCKS.register("obsidian_inscriptions",
		() -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(50f).explosionResistance(1200f)));
	public static final RegistryObject<Item> OBSIDIAN_INSCRIPTIONS_ITEM = fromBlock(OBSIDIAN_INSCRIPTIONS);

	public static final RegistryObject<FrameBlock> FRAME_BLOCK = BLOCKS.register("frame_block", FrameBlock::new);
	public static final RegistryObject<Item> FRAME_BLOCK_ITEM = fromBlock(FRAME_BLOCK);

	public static final RegistryObject<FrameSlope> FRAME_SLOPE = BLOCKS.register("frame_slope", FrameSlope::new);
	public static final RegistryObject<Item> FRAME_SLOPE_ITEM = fromBlock(FRAME_SLOPE);

	public static final RegistryObject<FrameStairs> FRAME_STAIRS = BLOCKS.register("frame_stairs", FrameStairs::new);
	public static final RegistryObject<Item> FRAME_STAIRS_ITEM = fromBlock(FRAME_STAIRS);

	public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
		return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
	}

}
