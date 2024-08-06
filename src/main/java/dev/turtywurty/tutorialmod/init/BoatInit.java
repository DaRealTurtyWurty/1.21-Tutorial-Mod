package dev.turtywurty.tutorialmod.init;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import dev.turtywurty.tutorialmod.TutorialMod;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class BoatInit {
    public static final Identifier EXAMPLE_BOAT_ID = TutorialMod.id("example_boat");
    public static final Identifier EXAMPLE_CHEST_BOAT_ID = TutorialMod.id("example_chest_boat");
    public static final RegistryKey<TerraformBoatType> EXAMPLE_BOAT_KEY = TerraformBoatTypeRegistry.createKey(EXAMPLE_BOAT_ID);

    public static final TerraformBoatType EXAMPLE_TYPE = register(EXAMPLE_BOAT_KEY, new TerraformBoatType.Builder()
            .item(ItemInit.EXAMPLE_BOAT)
            .chestItem(ItemInit.EXAMPLE_CHEST_BOAT)
            .planks(BlockInit.EXAMPLE_PLANKS.asItem())
            .build());

    public static TerraformBoatType register(RegistryKey<TerraformBoatType> key, TerraformBoatType type) {
        return Registry.register(TerraformBoatTypeRegistry.INSTANCE, key, type);
    }

    public static void load() {}
}
