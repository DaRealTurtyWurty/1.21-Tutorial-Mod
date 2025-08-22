package dev.turtywurty.tutorialmod.block;

import dev.turtywurty.tutorialmod.block.entity.ExampleRecipeBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityTypeInit;
import dev.turtywurty.tutorialmod.util.TickableBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExampleRecipeBlock extends Block implements BlockEntityProvider {
    private static final VoxelShape SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(0, 0, 0, 0.125, 0.5, 0.125),
            VoxelShapes.cuboid(0.875, 0, 0, 1, 0.5, 0.125),
            VoxelShapes.cuboid(0, 0, 0.875, 0.125, 0.5, 1),
            VoxelShapes.cuboid(0.875, 0, 0.875, 1, 0.5, 1),
            VoxelShapes.cuboid(0, 0.5, 0, 1, 0.625, 1),
            VoxelShapes.cuboid(0.9375, 0.625, 0, 1, 0.6875, 1),
            VoxelShapes.cuboid(0, 0.625, 0, 0.0625, 0.6875, 1),
            VoxelShapes.cuboid(0.0625, 0.625, 0.9375, 0.9375, 0.6875, 1),
            VoxelShapes.cuboid(0.0625, 0.625, 0, 0.9375, 0.6875, 0.0625),
            VoxelShapes.cuboid(0.9375, 0.4375, 0.125, 1, 0.5, 0.875),
            VoxelShapes.cuboid(0.125, 0.4375, 0.9375, 0.875, 0.5, 1),
            VoxelShapes.cuboid(0, 0.4375, 0.125, 0.0625, 0.5, 0.875),
            VoxelShapes.cuboid(0.125, 0.4375, 0, 0.875, 0.5, 0.0625),
            VoxelShapes.cuboid(0.9375, 0.375, 0.1875, 1, 0.4375, 0.8125),
            VoxelShapes.cuboid(0, 0.375, 0.1875, 0.0625, 0.4375, 0.8125),
            VoxelShapes.cuboid(0.1875, 0.375, 0, 0.8125, 0.4375, 0.0625),
            VoxelShapes.cuboid(0.1875, 0.375, 0.9375, 0.8125, 0.4375, 1),
            VoxelShapes.cuboid(0.375, 0.625, 0.375, 0.625, 0.6875, 0.625)
    ).simplify();

    public ExampleRecipeBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            if (world.getBlockEntity(pos) instanceof ExampleRecipeBlockEntity recipeBlockEntity) {
                player.openHandledScreen(recipeBlockEntity);
            }
        }

        return ActionResult.success(world.isClient);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeInit.EXAMPLE_RECIPE_BLOCK_ENTITY.instantiate(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return TickableBlockEntity.getTicker(world);
    }
}
