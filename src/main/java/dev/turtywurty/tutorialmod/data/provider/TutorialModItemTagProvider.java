package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
import dev.turtywurty.tutorialmod.list.TagList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class TutorialModItemTagProvider extends FabricTagProvider<Item> {
    public TutorialModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ItemInit.EXAMPLE_SWORD);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ItemInit.EXAMPLE_PICKAXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ItemInit.EXAMPLE_SHOVEL);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ItemInit.EXAMPLE_AXE);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ItemInit.EXAMPLE_HOE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ItemInit.EXAMPLE_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ItemInit.EXAMPLE_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ItemInit.EXAMPLE_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ItemInit.EXAMPLE_BOOTS);

        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS)
                .add(BlockInit.EXAMPLE_FLOWER.asItem());
        
        getOrCreateTagBuilder(TagList.Items.EXAMPLE_LOGS)
                .add(BlockInit.EXAMPLE_LOG.asItem())
                .add(BlockInit.STRIPPED_EXAMPLE_LOG.asItem());
        
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(TagList.Items.EXAMPLE_LOGS);

        getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(BlockInit.EXAMPLE_LEAVES.asItem());

        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(BlockInit.EXAMPLE_SAPLING.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(BlockInit.EXAMPLE_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(BlockInit.EXAMPLE_DOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(BlockInit.EXAMPLE_FENCE.asItem());

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(BlockInit.EXAMPLE_FENCE_GATE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(BlockInit.EXAMPLE_STAIRS.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(BlockInit.EXAMPLE_SLAB.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(BlockInit.EXAMPLE_PRESSURE_PLATE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(BlockInit.EXAMPLE_TRAPDOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(BlockInit.EXAMPLE_BUTTON.asItem());

        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(ItemInit.EXAMPLE_SIGN);

        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(ItemInit.EXAMPLE_HANGING_SIGN);

        getOrCreateTagBuilder(ItemTags.BOATS)
                .add(ItemInit.EXAMPLE_BOAT);

        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(ItemInit.EXAMPLE_CHEST_BOAT);
    }
}
