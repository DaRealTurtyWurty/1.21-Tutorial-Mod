package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.list.FoodList;
import dev.turtywurty.tutorialmod.list.enums.TutorialModToolMaterials;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ItemInit {
    public static final List<ItemConvertible> BLACKLIST = new ArrayList<>();

    public static final Item EXAMPLE_ITEM = register("example_item", new Item(new Item.Settings()));
    public static final Item EXAMPLE_FOOD = register("example_food",
            new Item(new Item.Settings().food(FoodList.EXAMPLE_FOOD_COMPONENT).maxCount(16)));

    public static final SwordItem EXAMPLE_SWORD = register("example_sword",
            new SwordItem(TutorialModToolMaterials.EXAMPLE, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(TutorialModToolMaterials.EXAMPLE, 3, -2.4f))));

    public static final PickaxeItem EXAMPLE_PICKAXE = register("example_pickaxe",
            new PickaxeItem(TutorialModToolMaterials.EXAMPLE, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(TutorialModToolMaterials.EXAMPLE, 1, -2.8F))));

    public static final AxeItem EXAMPLE_AXE = register("example_axe",
            new AxeItem(TutorialModToolMaterials.EXAMPLE, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(TutorialModToolMaterials.EXAMPLE, 5, -3.0F))));

    public static final ShovelItem EXAMPLE_SHOVEL = register("example_shovel",
            new ShovelItem(TutorialModToolMaterials.EXAMPLE, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(TutorialModToolMaterials.EXAMPLE, 1.5F, -3.0F))));

    public static final HoeItem EXAMPLE_HOE = register("example_hoe",
            new HoeItem(TutorialModToolMaterials.EXAMPLE, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(TutorialModToolMaterials.EXAMPLE, 0, -3.0F))));

    public static final ArmorItem EXAMPLE_HELMET = register("example_helmet",
            new ArmorItem(ArmorMaterialInit.EXAMPLE, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(45))));

    public static final ArmorItem EXAMPLE_CHESTPLATE = register("example_chestplate",
            new ArmorItem(ArmorMaterialInit.EXAMPLE, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(45))));

    public static final ArmorItem EXAMPLE_LEGGINGS = register("example_leggings",
            new ArmorItem(ArmorMaterialInit.EXAMPLE, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(45))));

    public static final ArmorItem EXAMPLE_BOOTS = register("example_boots",
            new ArmorItem(ArmorMaterialInit.EXAMPLE, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(45))));

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, TutorialMod.id(name), item);
    }

    public static void load() {}
}
