package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.recipe.ExampleRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RecipeTypeInit {
    public static final RecipeType<ExampleRecipe> EXAMPLE_RECIPE = register("example_recipe", ExampleRecipe.Type.INSTANCE);

    public static <T extends Recipe<?>> RecipeType<T> register(String id, RecipeType<T> type) {
        return Registry.register(Registries.RECIPE_TYPE, TutorialMod.id(id), type);
    }

    public static void load() {}
}
