package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.entity.ExampleEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityTypeInit {
    public static final EntityType<ExampleEntity> EXAMPLE_ENTITY =
            register("example_entity",
                    FabricEntityType.Builder.<ExampleEntity>createMob(
                                    ExampleEntity::new,
                                    SpawnGroup.CREATURE,
                                    mob -> mob.defaultAttributes(ExampleEntity::createDefaultAttributes))
                            .dimensions(1f, 1.5f)
                            .eyeHeight(1.0625f));

    public static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        Identifier id = TutorialMod.id(name);
        return Registry.register(Registries.ENTITY_TYPE, id, builder.build(id.toString()));
    }

    public static void load() {
        // NO-OP
    }
}
