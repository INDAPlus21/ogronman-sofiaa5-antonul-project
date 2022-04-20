package se.datasektionen.lava.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext.Builder;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import se.datasektionen.lava.blocks.utils.ModelProperty;
import se.datasektionen.lava.setup.Registration;

public class FrameBlock extends Block {

	public static final EnumProperty<ModelProperty> TEXTURE = EnumProperty.create("model", ModelProperty.class);

	public static ArrayList<String> eligibleBlocks = null;

	public FrameBlock() {
		super(Properties.of(Material.WOOD).noOcclusion().strength(2.0f));
		registerDefaultState(this.defaultBlockState().setValue(TEXTURE, ModelProperty.frame_block));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		ItemStack held = player.getItemInHand(hand);
		Item heldItem = held.getItem();
		if (!level.isClientSide && heldItem == null) {
			if (state.getValue(TEXTURE) != ModelProperty.frame_block) {
				String item_name = state.getValue(TEXTURE).toString();
				CompoundTag tag = new CompoundTag();
				ItemStack item_stack = new ItemStack(Registration.OBSIDIAN_INSCRIPTIONS_ITEM.get());
				player.addItem(item_stack);
			}
			return InteractionResult.PASS;
		} else if (heldItem == Registration.FRAME_BLOCK_ITEM.get()) {
			return InteractionResult.PASS;
		} else if (heldItem != null && eligibleBlocks.contains(heldItem.toString())) {
			ModelProperty newProperty = ModelProperty.valueOf(heldItem.toString());
			state = state.setValue(TEXTURE, newProperty);
			level.setBlock(pos, state, 10);
			return InteractionResult.CONSUME;
		}
		return InteractionResult.FAIL;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(TEXTURE);
	}

	public static void setupFrame() {
		ModelProperty[] tempVal = ModelProperty.values();
		eligibleBlocks = new ArrayList<>();

		for (int i = 0; i < tempVal.length; i++) {
			eligibleBlocks.add(i, tempVal[i].toString());
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(TEXTURE, ModelProperty.frame_block);
	}

}
