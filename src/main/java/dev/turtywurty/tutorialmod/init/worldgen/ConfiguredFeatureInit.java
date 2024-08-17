package dev.turtywurty.tutorialmod.init.worldgen;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ConfiguredFeatureInit {
    public static final RegistryKey<ConfiguredFeature<?, ?>> OVERWORLD_EXAMPLE_ORE_KEY = registerKey("overworld_example_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_EXAMPLE_ORE_KEY = registerKey("nether_example_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> END_EXAMPLE_ORE_KEY = registerKey("end_example_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> EXAMPLE_FLOWER_KEY = registerKey("example_flower");
    public static final RegistryKey<ConfiguredFeature<?, ?>> EXAMPLE_FLOWER_PATCH_KEY = registerKey("example_flower_patch");

    public static final RegistryKey<ConfiguredFeature<?, ?>> EXAMPLE_TREE_KEY = registerKey("example_tree");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneOreReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherOreReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endOreReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldExampleTargets = List.of(
                OreFeatureConfig.createTarget(stoneOreReplaceables, BlockInit.EXAMPLE_OVERWORLD_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateOreReplaceables, BlockInit.EXAMPLE_DEEPSLATE_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> netherExampleTargets = List.of(
                OreFeatureConfig.createTarget(netherOreReplaceables, BlockInit.EXAMPLE_NETHER_ORE.getDefaultState()));

        List<OreFeatureConfig.Target> endExampleTargets = List.of(
                OreFeatureConfig.createTarget(endOreReplaceables, BlockInit.EXAMPLE_END_ORE.getDefaultState()));

        register(context, OVERWORLD_EXAMPLE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldExampleTargets, 9));
        register(context, NETHER_EXAMPLE_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherExampleTargets, 9));
        register(context, END_EXAMPLE_ORE_KEY, Feature.ORE, new OreFeatureConfig(endExampleTargets, 9));

        RegistryEntryLookup<PlacedFeature> registryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        register(context, EXAMPLE_FLOWER_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                BlockStateProvider.of(BlockInit.EXAMPLE_FLOWER)));

        register(context, EXAMPLE_FLOWER_PATCH_KEY, Feature.FLOWER,
                new RandomPatchFeatureConfig(
                        64,
                        10,
                        4,
                        registryLookup.getOrThrow(PlacedFeatureInit.EXAMPLE_FLOWER_KEY)));

        register(context, EXAMPLE_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                SimpleBlockStateProvider.of(BlockInit.EXAMPLE_LOG), // log
                new StraightTrunkPlacer(4, 4, 6), // baseHeight, firstRandomHeight, secondRandomHeight

                SimpleBlockStateProvider.of(BlockInit.EXAMPLE_LEAVES), // leaves
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2), // radius, offset, height

                new TwoLayersFeatureSize(3, 0, 3) // limit, lowerSize, upperSize
        ).build());
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, TutorialMod.id(name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key,
                                                                                   F feature,
                                                                                   FC featureConfig) {
        context.register(key, new ConfiguredFeature<>(feature, featureConfig));
    }
}
