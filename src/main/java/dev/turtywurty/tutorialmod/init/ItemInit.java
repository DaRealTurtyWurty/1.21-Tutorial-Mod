package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.list.FoodList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<ItemConvertible> BLACKLIST = new ArrayList<>();

    public static final Item EXAMPLE_ITEM = register("example_item", new Item(new Item.Settings()));
    public static final Item EXAMPLE_FOOD = register("example_food",
            new Item(new Item.Settings().food(FoodList.EXAMPLE_FOOD_COMPONENT).maxCount(16)));

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, TutorialMod.id(name), item);
    }

    public static void load() {}
}
