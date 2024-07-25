package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.init.BlockInit;
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
    }
}