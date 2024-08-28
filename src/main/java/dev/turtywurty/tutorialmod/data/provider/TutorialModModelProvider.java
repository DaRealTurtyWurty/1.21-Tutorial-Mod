package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.family.BlockFamily;

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
    
        blockStateModelGenerator.registerLog(BlockInit.EXAMPLE_LOG)
                .log(BlockInit.EXAMPLE_LOG)
                .wood(BlockInit.EXAMPLE_WOOD);

        blockStateModelGenerator.registerLog(BlockInit.STRIPPED_EXAMPLE_LOG)
                .log(BlockInit.STRIPPED_EXAMPLE_LOG)
                .wood(BlockInit.STRIPPED_EXAMPLE_WOOD);
        
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.EXAMPLE_LEAVES);
        blockStateModelGenerator.registerTintableCross(BlockInit.EXAMPLE_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerHangingSign(BlockInit.STRIPPED_EXAMPLE_LOG, BlockInit.EXAMPLE_HANGING_SIGN, BlockInit.EXAMPLE_WALL_HANGING_SIGN);
        
        var exampleFamily = new BlockFamily.Builder(BlockInit.EXAMPLE_PLANKS)
                .button(BlockInit.EXAMPLE_BUTTON)
                .fence(BlockInit.EXAMPLE_FENCE)
                .fenceGate(BlockInit.EXAMPLE_FENCE_GATE)
                .pressurePlate(BlockInit.EXAMPLE_PRESSURE_PLATE)
                .sign(BlockInit.EXAMPLE_SIGN, BlockInit.EXAMPLE_WALL_SIGN)
                .slab(BlockInit.EXAMPLE_SLAB)
                .stairs(BlockInit.EXAMPLE_STAIRS)
                .door(BlockInit.EXAMPLE_DOOR)
                .trapdoor(BlockInit.EXAMPLE_TRAPDOOR)
                .group("wooden")
                .unlockCriterionName("has_planks")
                .build();
        blockStateModelGenerator.registerCubeAllModelTexturePool(exampleFamily.getBaseBlock())
                .family(exampleFamily);

        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.EXAMPLE_BE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.EXAMPLE_TICKING_BE_BLOCK);
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
        itemModelGenerator.register(ItemInit.EXAMPLE_BOAT, Models.GENERATED);
        itemModelGenerator.register(ItemInit.EXAMPLE_CHEST_BOAT, Models.GENERATED);
    }
}
