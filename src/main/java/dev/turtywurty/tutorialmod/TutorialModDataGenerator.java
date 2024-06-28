package dev.turtywurty.tutorialmod;

import dev.turtywurty.tutorialmod.data.provider.TutorialModBlockLootTableProvider;
import dev.turtywurty.tutorialmod.data.provider.TutorialModBlockTagProvider;
import dev.turtywurty.tutorialmod.data.provider.TutorialModEnglishLanguageProvider;
import dev.turtywurty.tutorialmod.data.provider.TutorialModModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(TutorialModModelProvider::new);
		pack.addProvider(TutorialModEnglishLanguageProvider::new);
		pack.addProvider(TutorialModBlockLootTableProvider::new);
		pack.addProvider(TutorialModBlockTagProvider::new);
	}
}
