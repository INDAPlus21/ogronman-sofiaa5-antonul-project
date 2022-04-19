package se.datasektionen.lava.blocks;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import se.datasektionen.lava.LavaMod;
import se.datasektionen.lava.setup.Registration;

public class SlopeBlock extends HorizontalDirectionalBlock {
	
	
	private static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
	
	public SlopeBlock() {
		super(Properties.of(Material.WOOD).noOcclusion().strength(2.0f).requiresCorrectToolForDrops());
		registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
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

	
	/**
	 * A function that makes the block always face the player when placed
	 */
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
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

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
	
	

}
