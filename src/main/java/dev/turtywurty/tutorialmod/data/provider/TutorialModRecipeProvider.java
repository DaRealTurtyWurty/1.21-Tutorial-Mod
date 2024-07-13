package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TutorialModRecipeProvider extends FabricRecipeProvider {
    public TutorialModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.EXAMPLE_BLOCK)
                .input('E', ItemInit.EXAMPLE_ITEM)
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .criterion(hasItem(ItemInit.EXAMPLE_ITEM), conditionsFromItem(ItemInit.EXAMPLE_ITEM))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ItemInit.EXAMPLE_ITEM, 9)
                .input(BlockInit.EXAMPLE_BLOCK)
                .criterion(hasItem(BlockInit.EXAMPLE_BLOCK), conditionsFromItem(BlockInit.EXAMPLE_BLOCK))
                .offerTo(exporter);

        List<ItemConvertible> exampleOres = List.of(BlockInit.EXAMPLE_OVERWORLD_ORE, BlockInit.EXAMPLE_DEEPSLATE_ORE, BlockInit.EXAMPLE_NETHER_ORE, BlockInit.EXAMPLE_END_ORE);

        RecipeProvider.offerBlasting(exporter,
                exampleOres,
                RecipeCategory.MISC,
                ItemInit.EXAMPLE_ITEM,
                0.2f,
                100,
                "example");

        RecipeProvider.offerSmelting(exporter,
                exampleOres,
                RecipeCategory.MISC,
                ItemInit.EXAMPLE_ITEM,
                0.2f,
                200,
                "example");

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ItemInit.EXAMPLE_SWORD)
                .input('E', ItemInit.EXAMPLE_ITEM)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("E")
                .pattern("E")
                .pattern("S")
                .criterion(hasItem(ItemInit.EXAMPLE_ITEM), conditionsFromItem(ItemInit.EXAMPLE_ITEM))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInit.EXAMPLE_PICKAXE)
                .input('E', ItemInit.EXAMPLE_ITEM)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("EEE")
                .pattern(" S ")
                .pattern(" S ")
                .criterion(hasItem(ItemInit.EXAMPLE_ITEM), conditionsFromItem(ItemInit.EXAMPLE_ITEM))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInit.EXAMPLE_AXE)
                .input('E', ItemInit.EXAMPLE_ITEM)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("EE")
                .pattern("ES")
                .pattern(" S")
                .criterion(hasItem(ItemInit.EXAMPLE_ITEM), conditionsFromItem(ItemInit.EXAMPLE_ITEM))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInit.EXAMPLE_SHOVEL)
                .input('E', ItemInit.EXAMPLE_ITEM)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("E")
                .pattern("S")
                .pattern("S")
                .criterion(hasItem(ItemInit.EXAMPLE_ITEM), conditionsFromItem(ItemInit.EXAMPLE_ITEM))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ItemInit.EXAMPLE_HOE)
                .input('E', ItemInit.EXAMPLE_ITEM)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("EE")
                .pattern(" S")
                .pattern(" S")
                .criterion(hasItem(ItemInit.EXAMPLE_ITEM), conditionsFromItem(ItemInit.EXAMPLE_ITEM))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);
    }

    private static @NotNull String hasTag(@NotNull TagKey<Item> tag) {
        return "has_" + tag.id().toString();
    }
}
