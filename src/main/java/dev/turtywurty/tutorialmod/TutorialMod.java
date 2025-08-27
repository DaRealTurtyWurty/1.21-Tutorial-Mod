package dev.turtywurty.tutorialmod;

import dev.turtywurty.tutorialmod.block.entity.*;
import dev.turtywurty.tutorialmod.init.*;
import dev.turtywurty.tutorialmod.init.worldgen.BiomeModificationInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
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
        ScreenHandlerTypeInit.load();
        RecipeTypeInit.load();
        RecipeSerializerInit.load();
        EntityTypeInit.load();

        // Event handling
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.PUMPKIN_PIE, ItemInit.EXAMPLE_FOOD);
        });

        EnergyStorage.SIDED.registerForBlockEntity(ExampleEnergyGeneratorBlockEntity::getEnergyProvider, BlockEntityTypeInit.EXAMPLE_ENERGY_GENERATOR);
        EnergyStorage.SIDED.registerForBlockEntity(ExampleEnergyStorageBlockEntity::getEnergyProvider, BlockEntityTypeInit.EXAMPLE_ENERGY_STORAGE);
        ItemStorage.SIDED.registerForBlockEntity(ExampleInventoryBlockEntity::getInventoryProvider, BlockEntityTypeInit.EXAMPLE_INVENTORY_BLOCK_ENTITY);
        ItemStorage.SIDED.registerForBlockEntity(ExampleFluidTankBlockEntity::getInventoryProvider, BlockEntityTypeInit.EXAMPLE_FLUID_TANK);
        FluidStorage.SIDED.registerForBlockEntity(ExampleFluidTankBlockEntity::getFluidTankProvider, BlockEntityTypeInit.EXAMPLE_FLUID_TANK);
        ItemStorage.SIDED.registerForBlockEntity(ExampleRecipeBlockEntity::getInventoryProvider, BlockEntityTypeInit.EXAMPLE_RECIPE_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity(ExampleRecipeBlockEntity::getEnergyProvider, BlockEntityTypeInit.EXAMPLE_RECIPE_BLOCK_ENTITY);

        LOGGER.info("Loaded!");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}