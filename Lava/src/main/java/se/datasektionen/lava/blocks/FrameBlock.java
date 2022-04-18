package se.datasektionen.lava.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.FrontAndTop;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import se.datasektionen.lava.blocks.utils.BlockModel;
import se.datasektionen.lava.setup.Registration;

public class FrameBlock extends Block {

	public static final EnumProperty<BlockModel> TEXTURE = EnumProperty.create("model", BlockModel.class);

	public FrameBlock() {
		super(Properties.of(Material.WOOD).noOcclusion().strength(2.0f).requiresCorrectToolForDrops());
		registerDefaultState(this.defaultBlockState().setValue(TEXTURE, BlockModel.FRAME));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		ItemStack held = player.getItemInHand(hand);
		if (!level.isClientSide && held.getItem() == null) {
			Pig pig = EntityType.PIG.create(level);
			pig.setPos(pos.getX() + this.RANDOM.nextInt(10) - 5, pos.getY(), pos.getZ() + this.RANDOM.nextInt(10) - 5);
			level.addFreshEntity(pig);
			return InteractionResult.CONSUME;
		} else if (held.getItem() == Registration.FRAME_BLOCK_ITEM.get()) {
			return InteractionResult.PASS;
		} else if (held.getItem() == Registration.OBSIDIAN_INSCRIPTIONS_ITEM.get()) {
			state = state.setValue(TEXTURE, BlockModel.INSCRIPTION);
			level.setBlock(pos, state, 10);
			return InteractionResult.CONSUME;
		} else if (held.getItem() == Items.STONE) {
			state = state.setValue(TEXTURE, BlockModel.STONE);
			level.setBlock(pos, state, 10);
			return InteractionResult.CONSUME;
		}
		return InteractionResult.FAIL;
	}
	
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(TEXTURE);
	}
	
	
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(TEXTURE, BlockModel.FRAME);
	}
	
}
