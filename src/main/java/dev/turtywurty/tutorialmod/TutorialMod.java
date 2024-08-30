package dev.turtywurty.tutorialmod;

import dev.turtywurty.tutorialmod.block.entity.ExampleEnergyGeneratorBlockEntity;
import dev.turtywurty.tutorialmod.block.entity.ExampleEnergyStorageBlockEntity;
import dev.turtywurty.tutorialmod.init.*;
import dev.turtywurty.tutorialmod.init.worldgen.BiomeModificationInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import team.reborn.energy.api.EnergyStorage;

public class TutorialMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("TutorialMod");
    public static final String MOD_ID = "tutorialmod";

    @Override
    public void onInitialize() {
        LOGGER.info("Loading...");

        // Load init classes
        ItemInit.load();
        BlockInit.load();
        ItemGroupInit.load();
        BiomeModificationInit.load();
        ArmorMaterialInit.load();
        BoatInit.load();
        EnchantmentInit.load();
        BlockEntityTypeInit.load();

        // Event handling
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.PUMPKIN_PIE, ItemInit.EXAMPLE_FOOD);
        });

        EnergyStorage.SIDED.registerForBlockEntity(ExampleEnergyGeneratorBlockEntity::getEnergyProvider, BlockEntityTypeInit.EXAMPLE_ENERGY_GENERATOR);
        EnergyStorage.SIDED.registerForBlockEntity(ExampleEnergyStorageBlockEntity::getEnergyProvider, BlockEntityTypeInit.EXAMPLE_ENERGY_STORAGE);

        LOGGER.info("Loaded!");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}