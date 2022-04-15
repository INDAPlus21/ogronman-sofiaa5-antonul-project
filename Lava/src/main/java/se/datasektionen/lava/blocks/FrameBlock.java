package se.datasektionen.lava.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class FrameBlock extends Block {
    public FrameBlock() {
        super(Properties.of(Material.WOOD)
                .noOcclusion()
                .strength(2.0f)
                .requiresCorrectToolForDrops());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
                                 BlockHitResult result) {
        if (!level.isClientSide) {
                Pig pig = EntityType.PIG.create(level);
                pig.setPos(pos.getX() + this.RANDOM.nextInt(10) - 5, pos.getY(),
                        pos.getZ() + this.RANDOM.nextInt(10) - 5);
                level.addFreshEntity(pig);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
