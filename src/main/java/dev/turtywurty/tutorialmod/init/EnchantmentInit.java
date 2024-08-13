package dev.turtywurty.tutorialmod.init;

import com.mojang.serialization.MapCodec;
import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.enchantments.effects.LightningEnchantmentEffect;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class EnchantmentInit {
    public static final RegistryKey<Enchantment> THUNDERING_KEY = RegistryKey.of(RegistryKeys.ENCHANTMENT, TutorialMod.id("thundering"));

    public static final MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning", LightningEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, TutorialMod.id(name), codec);
    }

    public static void load() {}
}
