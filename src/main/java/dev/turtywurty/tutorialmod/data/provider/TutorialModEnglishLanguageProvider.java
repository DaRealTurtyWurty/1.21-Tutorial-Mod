package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ItemGroupInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
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
    }
}
