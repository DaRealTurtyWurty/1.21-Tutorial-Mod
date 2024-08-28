package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.block.entity.ExampleBlockEntity;
import dev.turtywurty.tutorialmod.block.entity.ExampleTickingBlockEntity;
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

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, TutorialMod.id(name), type);
    }

    public static void load() {}
}
