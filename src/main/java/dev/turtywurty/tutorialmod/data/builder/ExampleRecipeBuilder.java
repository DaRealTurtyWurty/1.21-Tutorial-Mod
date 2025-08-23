package dev.turtywurty.tutorialmod.data.builder;

import dev.turtywurty.tutorialmod.recipe.ExampleRecipe;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.List;

public record ExampleRecipeBuilder(Ingredient inputA, Ingredient inputB, ItemStack output,
                                   int processTime, int energyCost) {
    public static ExampleRecipeBuilder create(Ingredient inputA, Ingredient inputB, ItemStack output,
                                              int processTime, int energyCost) {
        return new ExampleRecipeBuilder(inputA, inputB, output, processTime, energyCost);
    }

    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        exporter.accept(recipeId,
                new ExampleRecipe(List.of(inputA, inputB), output, processTime, energyCost),
                null);
    }
}
