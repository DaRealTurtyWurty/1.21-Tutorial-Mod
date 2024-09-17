package dev.turtywurty.tutorialmod.block;

import dev.turtywurty.tutorialmod.block.entity.ExampleInventoryBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockEntityTypeInit;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ExampleInventoryBlock extends Block implements BlockEntityProvider {
    private static final VoxelShape DEFAULT_SHAPE = VoxelShapes.union(
            VoxelShapes.cuboid(0.125, 0, 0.125, 0.875, 0.5625, 0.875),
            VoxelShapes.cuboid(0.125, 0.5625, 0.125, 0.875, 0.75, 0.875),
            VoxelShapes.cuboid(0.4375, 0.4375, 0.0625, 0.5625, 0.625, 0.125)
    ).simplify();
    private static final Map<Direction, VoxelShape> SHAPES = new HashMap<>();

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public ExampleInventoryBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));

        for (Direction direction : Direction.values()) {
            SHAPES.put(direction, calculateShapes(direction, DEFAULT_SHAPE));
        }
    }

    private static VoxelShape calculateShapes(Direction to, VoxelShape shape) {
        final VoxelShape[] buffer = {shape, VoxelShapes.empty()};

        final int times = (to.getHorizontal() - Direction.NORTH.getHorizontal() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) ->
                    buffer[1] = VoxelShapes.union(buffer[1],
                            VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient) {
            if(world.getBlockEntity(pos) instanceof ExampleInventoryBlockEntity inventoryBlockEntity) {
                player.openHandledScreen(inventoryBlockEntity);
            }
        }

        return ActionResult.success(world.isClient);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityTypeInit.EXAMPLE_INVENTORY_BLOCK_ENTITY.instantiate(pos, state);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(FACING));
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }
}
