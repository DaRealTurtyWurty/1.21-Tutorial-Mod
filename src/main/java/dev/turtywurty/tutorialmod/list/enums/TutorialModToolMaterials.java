package dev.turtywurty.tutorialmod.list.enums;

import dev.turtywurty.tutorialmod.init.ItemInit;
import dev.turtywurty.tutorialmod.list.TagList;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

public enum TutorialModToolMaterials implements ToolMaterial {
    EXAMPLE(
            3430,
            12.0f,
            5.0f,
            TagList.Blocks.INCORRECT_FOR_EXAMPLE_TOOL,
            15,
            Ingredient.ofItems(ItemInit.EXAMPLE_ITEM)
    );

    private final int durability;
    private final float miningSpeedMultiplier, attackDamage;
    private final TagKey<Block> inverseTag;
    private final int enchantability;
    private final Ingredient repairIngredient;

    TutorialModToolMaterials(int durability, float miningSpeedMultiplier, float attackDamage, TagKey<Block> inverseTag, int enchantability, Ingredient repairIngredient) {
        this.durability = durability;
        this.miningSpeedMultiplier = miningSpeedMultiplier;
        this.attackDamage = attackDamage;
        this.inverseTag = inverseTag;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeedMultiplier;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }
}
