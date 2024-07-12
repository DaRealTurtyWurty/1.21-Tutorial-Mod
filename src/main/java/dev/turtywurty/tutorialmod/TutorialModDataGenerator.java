package dev.turtywurty.tutorialmod;

import dev.turtywurty.tutorialmod.data.generator.TutorialModWorldGenerator;
import dev.turtywurty.tutorialmod.data.provider.*;
import dev.turtywurty.tutorialmod.init.worldgen.ConfiguredFeatureInit;
import dev.turtywurty.tutorialmod.init.worldgen.PlacedFeatureInit;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(TutorialModModelProvider::new);
		pack.addProvider(TutorialModEnglishLanguageProvider::new);
		pack.addProvider(TutorialModBlockLootTableProvider::new);
		pack.addProvider(TutorialModBlockTagProvider::new);
		pack.addProvider(TutorialModWorldGenerator::new);
		pack.addProvider(TutorialModRecipeProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureInit::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatureInit::bootstrap);
	}
}
