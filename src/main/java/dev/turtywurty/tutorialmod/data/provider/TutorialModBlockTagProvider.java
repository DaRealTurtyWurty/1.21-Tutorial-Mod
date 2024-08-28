package dev.turtywurty.tutorialmod.data.provider;

import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.list.TagList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class TutorialModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public TutorialModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockInit.EXAMPLE_BLOCK)
                .add(BlockInit.EXAMPLE_OVERWORLD_ORE)
                .add(BlockInit.EXAMPLE_DEEPSLATE_ORE)
                .add(BlockInit.EXAMPLE_NETHER_ORE)
                .add(BlockInit.EXAMPLE_END_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockInit.EXAMPLE_BE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockInit.EXAMPLE_TICKING_BE_BLOCK);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(BlockInit.EXAMPLE_BLOCK)
                .add(BlockInit.EXAMPLE_OVERWORLD_ORE)
                .add(BlockInit.EXAMPLE_DEEPSLATE_ORE)
                .add(BlockInit.EXAMPLE_NETHER_ORE)
                .add(BlockInit.EXAMPLE_END_ORE)
                .add(BlockInit.EXAMPLE_BE_BLOCK)
                .add(BlockInit.EXAMPLE_TICKING_BE_BLOCK);

        getOrCreateTagBuilder(TagList.Blocks.EXAMPLE_TAG)
                .add(BlockInit.EXAMPLE_BLOCK)
                .add(Blocks.BLUE_ORCHID);

        getOrCreateTagBuilder(TagList.Blocks.INCORRECT_FOR_EXAMPLE_TOOL);

        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(BlockInit.EXAMPLE_FLOWER);

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(BlockInit.EXAMPLE_FLOWER_POT);
        
        getOrCreateTagBuilder(TagList.Blocks.EXAMPLE_LOGS)
                .add(BlockInit.EXAMPLE_LOG)
                .add(BlockInit.STRIPPED_EXAMPLE_LOG)
                .add(BlockInit.EXAMPLE_WOOD)
                .add(BlockInit.STRIPPED_EXAMPLE_WOOD);
        
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(TagList.Blocks.EXAMPLE_LOGS);
        
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(BlockInit.EXAMPLE_LEAVES);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(BlockInit.EXAMPLE_SAPLING);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(BlockInit.EXAMPLE_BUTTON);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(BlockInit.EXAMPLE_DOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(BlockInit.EXAMPLE_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(BlockInit.EXAMPLE_FENCE);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(BlockInit.EXAMPLE_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(BlockInit.EXAMPLE_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(BlockInit.EXAMPLE_STAIRS);

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(BlockInit.EXAMPLE_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(BlockInit.EXAMPLE_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(BlockInit.EXAMPLE_HANGING_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(BlockInit.EXAMPLE_WALL_HANGING_SIGN);
    }
}
