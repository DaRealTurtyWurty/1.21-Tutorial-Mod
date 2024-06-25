package dev.turtywurty.tutorialmod.list;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FoodList {
    public static final FoodComponent EXAMPLE_FOOD_COMPONENT = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.3F)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 20 * 30, 1), 0.75F)
            .build();
}
