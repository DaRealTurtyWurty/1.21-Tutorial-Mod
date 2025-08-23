package dev.turtywurty.tutorialmod.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.turtywurty.tutorialmod.init.RecipeSerializerInit;
import dev.turtywurty.tutorialmod.init.RecipeTypeInit;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public record ExampleRecipe(List<Ingredient> ingredients, ItemStack output,
                            int processTime, long energyCost) implements Recipe<ExampleRecipeInput> {
    @Override
    public boolean matches(ExampleRecipeInput input, World world) {
        int size = ingredients.size();
        List<ItemStack> stacks = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ItemStack stack = input.getStackInSlot(i);
            if (stack.isEmpty()) continue;
            stacks.add(stack);
        }

        List<Ingredient> remaining = new ArrayList<>(ingredients);
        for (ItemStack stack : stacks) {
            boolean matched = false;

            for (int i = 0; i < remaining.size(); i++) {
                Ingredient ingredient = remaining.get(i);
                if (ingredient.test(stack)) {
                    remaining.remove(i);
                    matched = true;
                    break;
                }
            }

            if (!matched)
                return false;
        }

        return remaining.isEmpty();
    }

    @Override
    public ItemStack craft(ExampleRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return DefaultedList.copyOf(Ingredient.EMPTY, this.ingredients.toArray(new Ingredient[0]));
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializerInit.EXAMPLE_RECIPE;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeTypeInit.EXAMPLE_RECIPE;
    }

    public static class Serializer implements RecipeSerializer<ExampleRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        private static final MapCodec<ExampleRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.list(Ingredient.DISALLOW_EMPTY_CODEC).fieldOf("ingredients").forGetter(ExampleRecipe::ingredients),
                ItemStack.CODEC.fieldOf("output").forGetter(ExampleRecipe::output),
                Codec.INT.fieldOf("process_time").forGetter(ExampleRecipe::processTime),
                Codec.LONG.fieldOf("energy_cost").forGetter(ExampleRecipe::energyCost)
        ).apply(instance, ExampleRecipe::new));

        private static final PacketCodec<RegistryByteBuf, ExampleRecipe> PACKET_CODEC = PacketCodec.tuple(
                PacketCodecs.collection(ArrayList::new, Ingredient.PACKET_CODEC), ExampleRecipe::ingredients,
                ItemStack.PACKET_CODEC, ExampleRecipe::output,
                PacketCodecs.VAR_INT, ExampleRecipe::processTime,
                PacketCodecs.VAR_LONG, ExampleRecipe::energyCost,
                ExampleRecipe::new
        );

        @Override
        public MapCodec<ExampleRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ExampleRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    public static class Type implements RecipeType<ExampleRecipe> {
        public static final Type INSTANCE = new Type();

        private Type() {
            // Prevent instantiation
        }

        @Override
        public String toString() {
            return "example_recipe";
        }
    }
}
