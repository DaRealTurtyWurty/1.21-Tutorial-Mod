package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class BlockInit {
    public static final Block EXAMPLE_BLOCK = registerWithItem("example_block", new Block(AbstractBlock.Settings.create()
            .strength(1.5F, 6.0F)
            .requiresTool()));

    public static final Block EXAMPLE_OVERWORLD_ORE = registerWithItem("example_overworld_ore", new Block(AbstractBlock.Settings.create()
            .strength(3.0F, 3.0F)
            .requiresTool()));

    public static final Block EXAMPLE_DEEPSLATE_ORE = registerWithItem("example_deepslate_ore", new Block(AbstractBlock.Settings.create()
            .strength(4.5F, 3.0F)
            .requiresTool()));

    public static final Block EXAMPLE_NETHER_ORE = registerWithItem("example_nether_ore", new Block(AbstractBlock.Settings.create()
            .strength(3.0F, 3.0F)
            .requiresTool()));

    public static final Block EXAMPLE_END_ORE = registerWithItem("example_end_ore", new Block(AbstractBlock.Settings.create()
            .strength(3.0F, 3.0F)
            .requiresTool()));

    public static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, TutorialMod.id(name), block);
    }

    public static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        T registered = register(name, block);
        ItemInit.register(name, new BlockItem(registered, settings));
        return registered;
    }

    public static <T extends Block> T registerWithItem(String name, T block) {
        return registerWithItem(name, block, new Item.Settings());
    }

    public static void load() {}
}
