package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TutorialModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public TutorialModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockInit.EXAMPLE_BLOCK);
        addDrop(BlockInit.EXAMPLE_OVERWORLD_ORE);
        addDrop(BlockInit.EXAMPLE_DEEPSLATE_ORE);
        addDrop(BlockInit.EXAMPLE_NETHER_ORE);
        addDrop(BlockInit.EXAMPLE_END_ORE);
        addDrop(BlockInit.EXAMPLE_FLOWER);
        addPottedPlantDrops(BlockInit.EXAMPLE_FLOWER_POT);

        addDrop(BlockInit.EXAMPLE_LOG);
        addDrop(BlockInit.STRIPPED_EXAMPLE_LOG);
        addDrop(BlockInit.EXAMPLE_WOOD);
        addDrop(BlockInit.STRIPPED_EXAMPLE_WOOD);
        leavesDrops(BlockInit.EXAMPLE_LEAVES, BlockInit.EXAMPLE_SAPLING, SAPLING_DROP_CHANCE);
        addDrop(BlockInit.EXAMPLE_SAPLING);
        addDrop(BlockInit.EXAMPLE_PLANKS);
        addDrop(BlockInit.EXAMPLE_SLAB);
        addDrop(BlockInit.EXAMPLE_STAIRS);
        addDrop(BlockInit.EXAMPLE_FENCE);
        addDrop(BlockInit.EXAMPLE_FENCE_GATE);
        doorDrops(BlockInit.EXAMPLE_DOOR);
        addDrop(BlockInit.EXAMPLE_TRAPDOOR);
        addDrop(BlockInit.EXAMPLE_BUTTON);
        addDrop(BlockInit.EXAMPLE_PRESSURE_PLATE);
        addDrop(BlockInit.EXAMPLE_SIGN, ItemInit.EXAMPLE_SIGN);
        addDrop(BlockInit.EXAMPLE_WALL_SIGN, ItemInit.EXAMPLE_SIGN);
        addDrop(BlockInit.EXAMPLE_HANGING_SIGN, ItemInit.EXAMPLE_HANGING_SIGN);
        addDrop(BlockInit.EXAMPLE_WALL_HANGING_SIGN, ItemInit.EXAMPLE_HANGING_SIGN);

        addDrop(BlockInit.EXAMPLE_BE_BLOCK);
        addDrop(BlockInit.EXAMPLE_TICKING_BE_BLOCK);
    }
}