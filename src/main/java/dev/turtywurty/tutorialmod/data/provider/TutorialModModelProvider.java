package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class TutorialModModelProvider extends FabricModelProvider {
    public TutorialModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.EXAMPLE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.EXAMPLE_OVERWORLD_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.EXAMPLE_DEEPSLATE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.EXAMPLE_NETHER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.EXAMPLE_END_ORE);
        blockStateModelGenerator.registerFlowerPotPlant(BlockInit.EXAMPLE_FLOWER, BlockInit.EXAMPLE_FLOWER_POT, BlockStateModelGenerator.TintType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemInit.EXAMPLE_ITEM, Models.GENERATED);
        itemModelGenerator.register(ItemInit.EXAMPLE_FOOD, Models.GENERATED);
        itemModelGenerator.register(ItemInit.EXAMPLE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.EXAMPLE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.EXAMPLE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.EXAMPLE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.EXAMPLE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.EXAMPLE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ItemInit.EXAMPLE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ItemInit.EXAMPLE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ItemInit.EXAMPLE_BOOTS, Models.GENERATED);
    }
}
