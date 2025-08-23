package dev.turtywurty.tutorialmod.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import java.util.List;

public record ExampleRecipeInput(List<ItemStack> stacks) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks.get(slot);
    }

    @Override
    public int getSize() {
        return stacks.size();
    }
}
