package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ItemGroupInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
import dev.turtywurty.tutorialmod.list.TagList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TutorialModEnglishLanguageProvider extends FabricLanguageProvider {
    public TutorialModEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private static void addText(@NotNull TranslationBuilder builder, @NotNull Text text, @NotNull String value) {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            builder.add(translatableTextContent.getKey(), value);
        } else {
            TutorialMod.LOGGER.warn("Failed to add translation for text: {}", text.getString());
        }
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ItemInit.EXAMPLE_ITEM, "Example Item");
        translationBuilder.add(ItemInit.EXAMPLE_FOOD, "Example Food");
        translationBuilder.add(BlockInit.EXAMPLE_BLOCK, "Example Block");
        addText(translationBuilder, ItemGroupInit.EXAMPLE_TITLE, "Tutorial Mod");
        translationBuilder.add(BlockInit.EXAMPLE_OVERWORLD_ORE, "Example Ore");
        translationBuilder.add(BlockInit.EXAMPLE_DEEPSLATE_ORE, "Example Deepslate Ore");
        translationBuilder.add(BlockInit.EXAMPLE_NETHER_ORE, "Example Nether Ore");
        translationBuilder.add(BlockInit.EXAMPLE_END_ORE, "Example End Ore");
        translationBuilder.add(ItemInit.EXAMPLE_SWORD, "Example Sword");
        translationBuilder.add(ItemInit.EXAMPLE_PICKAXE, "Example Pickaxe");
        translationBuilder.add(ItemInit.EXAMPLE_AXE, "Example Axe");
        translationBuilder.add(ItemInit.EXAMPLE_SHOVEL, "Example Shovel");
        translationBuilder.add(ItemInit.EXAMPLE_HOE, "Example Hoe");
        translationBuilder.add(ItemInit.EXAMPLE_HELMET, "Example Helmet");
        translationBuilder.add(ItemInit.EXAMPLE_CHESTPLATE, "Example Chestplate");
        translationBuilder.add(ItemInit.EXAMPLE_LEGGINGS, "Example Leggings");
        translationBuilder.add(ItemInit.EXAMPLE_BOOTS, "Example Boots");
        translationBuilder.add(BlockInit.EXAMPLE_FLOWER, "Example Flower");
        translationBuilder.add(BlockInit.EXAMPLE_FLOWER_POT, "Example Flower Pot");

        translationBuilder.add(BlockInit.EXAMPLE_LOG, "Example Log");
        translationBuilder.add(BlockInit.STRIPPED_EXAMPLE_LOG, "Stripped Example Log");
        translationBuilder.add(BlockInit.EXAMPLE_WOOD, "Example Wood");
        translationBuilder.add(BlockInit.STRIPPED_EXAMPLE_WOOD, "Stripped Example Wood");
        translationBuilder.add(BlockInit.EXAMPLE_LEAVES, "Example Leaves");
        translationBuilder.add(BlockInit.EXAMPLE_SAPLING, "Example Sapling");
        translationBuilder.add(BlockInit.EXAMPLE_PLANKS, "Example Planks");
        translationBuilder.add(BlockInit.EXAMPLE_SLAB, "Example Slab");
        translationBuilder.add(BlockInit.EXAMPLE_STAIRS, "Example Stairs");
        translationBuilder.add(BlockInit.EXAMPLE_FENCE, "Example Fence");
        translationBuilder.add(BlockInit.EXAMPLE_FENCE_GATE, "Example Fence Gate");
        translationBuilder.add(BlockInit.EXAMPLE_DOOR, "Example Door");
        translationBuilder.add(BlockInit.EXAMPLE_TRAPDOOR, "Example Trapdoor");
        translationBuilder.add(BlockInit.EXAMPLE_BUTTON, "Example Button");
        translationBuilder.add(BlockInit.EXAMPLE_PRESSURE_PLATE, "Example Pressure Plate");
        translationBuilder.add(ItemInit.EXAMPLE_SIGN, "Example Sign");
        translationBuilder.add(ItemInit.EXAMPLE_HANGING_SIGN, "Example Hanging Sign");
        translationBuilder.add(ItemInit.EXAMPLE_BOAT, "Example Boat");
        translationBuilder.add(ItemInit.EXAMPLE_CHEST_BOAT, "Example Chest Boat");
        translationBuilder.add(TagList.Items.EXAMPLE_LOGS, "Example Logs");
    }
}
