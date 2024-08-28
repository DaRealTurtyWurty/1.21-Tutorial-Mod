package dev.turtywurty.tutorialmod.block;

import dev.turtywurty.tutorialmod.block.entity.ExampleTickingBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityTypeInit;
import dev.turtywurty.tutorialmod.util.TickableBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExampleTickingBEBlock extends Block implements BlockEntityProvider {
    public ExampleTickingBEBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeInit.EXAMPLE_TICKING_BLOCK_ENTITY.instantiate(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient) {
            if (world.getBlockEntity(pos) instanceof ExampleTickingBlockEntity blockEntity) {
                player.sendMessage(Text.of("Mining at: " + blockEntity.getMiningPos().toString()), true);
            }
        }

        return ActionResult.success(world.isClient);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return TickableBlockEntity.getTicker(world);
    }
}
