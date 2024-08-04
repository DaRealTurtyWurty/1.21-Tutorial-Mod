package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
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
    }
}
