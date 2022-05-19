package se.datasektionen.lava.blocks;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;
import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.blocks.utils.ModelProperty;
import se.datasektionen.lava.setup.Registration;

public class SlopeBlock extends HorizontalDirectionalBlock {
	
	
	private static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
	
	public static final EnumProperty<ModelProperty> TEXTURE = EnumProperty.create("model", ModelProperty.class);
	
	public static ArrayList<String> eligibleBlocks = null;
	
	
	public SlopeBlock() {
		super(Properties.of(Material.WOOD).noOcclusion().strength(2.0f).requiresCorrectToolForDrops());
		registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TEXTURE, ModelProperty.slope_block));
		runCalculation(SHAPE.orElse(Shapes.block()));
	}

	
	//The voxelshape of the slope
	private static final Optional<VoxelShape> SHAPE = Stream.of(
			Block.box(0, 0, 0, 16, 2, 16),
			Block.box(0, 2, 2, 16, 4, 16),
			Block.box(0, 4, 4, 16, 6, 16),
			Block.box(0, 6, 6, 16, 8, 16),
			Block.box(0, 8, 8, 16, 12, 16),
			Block.box(0, 12, 13, 16, 16, 16)
			//Block.box(0, 0, 0, 16, 23, 0),
			//Block.box(0, 0, 16, 16, 16, 16)
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR));

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES.get(state.getValue(FACING));
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

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		ItemStack held = player.getItemInHand(hand);
		Item heldItem = held.getItem();
		if (!level.isClientSide && heldItem == Items.AIR) {
			return InteractionResult.FAIL;
		} else if (heldItem == Registration.SLOPE_BLOCK_ITEM.get() || heldItem == Registration.FRAME_BLOCK_ITEM.get()) {
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
	
	
	private void removeState(BlockState state, Player player) {
		String item_name = state.getValue(TEXTURE).toString();
		ResourceLocation rl = new ResourceLocation(item_name);
		Item new_item = ForgeRegistries.ITEMS.getValue(rl);
		
		if (new_item == Items.AIR) {
			rl = new ResourceLocation(LavaMod.MODID,item_name);
			new_item = ForgeRegistries.ITEMS.getValue(rl);
		}
		
		
		player.addItem(new ItemStack(new_item));	
	}
	
	/**
	 * A function that makes the block always face the player when placed
	 */
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
		builder.add(TEXTURE);
	}
	
	/**
	 * For each direction there is in the world, calculate how the voxels (kinda hitboxes) should be set up 
	 * (which is done through the LavaMod.calculateShapes() function)
	 * 
	 * @param shape, the shape the calculations should be done for
	 */
	protected void runCalculation(VoxelShape shape) {
		for (Direction direction : Direction.values())
			SHAPES.put(direction, LavaMod.calculateShapes(direction, shape));
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
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TEXTURE, ModelProperty.slope_block);
	}
	
	

}
