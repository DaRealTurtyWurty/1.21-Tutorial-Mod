package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.block.entity.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BlockEntityTypeInit {
    public static final BlockEntityType<ExampleBlockEntity> EXAMPLE_BLOCK_ENTITY = register("example_block_entity",
            BlockEntityType.Builder.create(ExampleBlockEntity::new, BlockInit.EXAMPLE_BE_BLOCK)
                    .build());

    public static final BlockEntityType<ExampleTickingBlockEntity> EXAMPLE_TICKING_BLOCK_ENTITY = register("example_ticking_block_entity",
            BlockEntityType.Builder.create(ExampleTickingBlockEntity::new, BlockInit.EXAMPLE_TICKING_BE_BLOCK)
                    .build());

    public static final BlockEntityType<ExampleEnergyGeneratorBlockEntity> EXAMPLE_ENERGY_GENERATOR = register("example_energy_generator",
            BlockEntityType.Builder.create(ExampleEnergyGeneratorBlockEntity::new, BlockInit.EXAMPLE_ENERGY_GENERATOR_BLOCK)
                    .build());

    public static final BlockEntityType<ExampleEnergyStorageBlockEntity> EXAMPLE_ENERGY_STORAGE = register("example_energy_storage",
            BlockEntityType.Builder.create(ExampleEnergyStorageBlockEntity::new, BlockInit.EXAMPLE_ENERGY_STORAGE_BLOCK)
                    .build());

    public static final BlockEntityType<ExampleInventoryBlockEntity> EXAMPLE_INVENTORY_BLOCK_ENTITY = register("example_inventory_block_entity",
            BlockEntityType.Builder.create(ExampleInventoryBlockEntity::new, BlockInit.EXAMPLE_INVENTORY_BLOCK)
                    .build());

    public static final BlockEntityType<ExampleFluidTankBlockEntity> EXAMPLE_FLUID_TANK = register("example_fluid_tank",
            BlockEntityType.Builder.create(ExampleFluidTankBlockEntity::new, BlockInit.EXAMPLE_FLUID_TANK)
                    .build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, TutorialMod.id(name), type);
    }

    public static void load() {}
}
