package dev.turtywurty.tutorialmod.block;

import dev.turtywurty.tutorialmod.block.entity.ExampleEnergyStorageBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityTypeInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExampleEnergyStorageBlock extends Block implements BlockEntityProvider {
    public ExampleEnergyStorageBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient) {
            if(world.getBlockEntity(pos) instanceof ExampleEnergyStorageBlockEntity energyStorage) {
                player.sendMessage(Text.literal("Energy: " + energyStorage.getEnergyStorage().getAmount()), true);
            }
        }

        return ActionResult.success(world.isClient);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeInit.EXAMPLE_ENERGY_STORAGE.instantiate(pos, state);
    }
}
