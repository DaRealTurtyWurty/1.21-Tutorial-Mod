package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.recipe.ExampleRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RecipeSerializerInit {
    public static final RecipeSerializer<ExampleRecipe> EXAMPLE_RECIPE = register("example_recipe", ExampleRecipe.Serializer.INSTANCE);

    public static <T extends Recipe<?>> RecipeSerializer<T> register(String id, RecipeSerializer<T> type) {
        return Registry.register(Registries.RECIPE_SERIALIZER, TutorialMod.id(id), type);
    }

    public static void load() {}
}
