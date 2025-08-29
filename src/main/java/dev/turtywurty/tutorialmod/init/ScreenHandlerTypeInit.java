package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.network.BlockPosPayload;
import dev.turtywurty.tutorialmod.network.IntegerPayload;
import dev.turtywurty.tutorialmod.screenhandler.*;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerTypeInit {
    public static final ScreenHandlerType<ExampleEnergyGeneratorScreenHandler> EXAMPLE_ENERGY_GENERATOR =
            register("example_energy_generator", ExampleEnergyGeneratorScreenHandler::new, BlockPosPayload.PACKET_CODEC);

    public static final ScreenHandlerType<ExampleInventoryScreenHandler> EXAMPLE_INVENTORY_SCREEN_HANDLER =
            register("example_inventory", ExampleInventoryScreenHandler::new, BlockPosPayload.PACKET_CODEC);

    public static final ExtendedScreenHandlerType<ExampleFluidTankScreenHandler, BlockPosPayload> EXAMPLE_FLUID_TANK =
            register("example_fluid_tank", ExampleFluidTankScreenHandler::new, BlockPosPayload.PACKET_CODEC);

    public static final ExtendedScreenHandlerType<ExampleRecipeScreenHandler, BlockPosPayload> EXAMPLE_RECIPE_SCREEN_HANDLER =
            register("example_recipe", ExampleRecipeScreenHandler::new, BlockPosPayload.PACKET_CODEC);

    public static final ExtendedScreenHandlerType<ExampleEntityScreenHandler, IntegerPayload> EXAMPLE_ENTITY =
            register("example_entity", ExampleEntityScreenHandler::new, IntegerPayload.PACKET_CODEC);

    public static <T extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<T, D> register(String name, ExtendedScreenHandlerType.ExtendedFactory<T, D> factory, PacketCodec<? super RegistryByteBuf, D> codec) {
        return Registry.register(Registries.SCREEN_HANDLER, TutorialMod.id(name), new ExtendedScreenHandlerType<>(factory, codec));
    }

    public static void load() {}
}
