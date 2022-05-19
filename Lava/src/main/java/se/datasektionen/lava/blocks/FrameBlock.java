package se.datasektionen.lava.blocks;

import java.util.ArrayList;
import se.datasektionen.lava.LavaMod;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
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
		if (!level.isClientSide && heldItem == Items.AIR) {
			return InteractionResult.FAIL;
		} else if (heldItem == Registration.FRAME_BLOCK_ITEM.get()) {
			return InteractionResult.PASS;
		} else if (heldItem != null && eligibleBlocks.contains(heldItem.toString())) {
			ModelProperty newProperty = ModelProperty.valueOf(heldItem.toString());
			if (newProperty == state.getValue(TEXTURE)) {
				return InteractionResult.PASS;
			} else {
				removeState(state, player);
				state = state.setValue(TEXTURE, newProperty);
				level.setBlock(pos, state, 10);
				held.shrink(1);
				player.setItemInHand(hand, held);
				return InteractionResult.CONSUME;
			}
		}
		return InteractionResult.FAIL;
	}

	@Override
	public void attack(BlockState state, Level level, BlockPos pos, Player player) {

		if (!level.isClientSide) {
			if (state.getValue(TEXTURE) != ModelProperty.frame_block) {

				removeState(state, player);

				state = state.setValue(TEXTURE, ModelProperty.frame_block);
				level.setBlock(pos, state, 10);
			}
		}
	}

	private void removeState(BlockState state, Player player) {
		String item_name = state.getValue(TEXTURE).toString();
		ResourceLocation rl = new ResourceLocation(item_name);
		Item new_item = ForgeRegistries.ITEMS.getValue(rl);

		if (new_item == Items.AIR) {
			rl = new ResourceLocation(LavaMod.MODID, item_name);
			new_item = ForgeRegistries.ITEMS.getValue(rl);
		} else if (new_item != Registration.FRAME_BLOCK_ITEM.get()) {
			player.addItem(new ItemStack(new_item));
		}
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
