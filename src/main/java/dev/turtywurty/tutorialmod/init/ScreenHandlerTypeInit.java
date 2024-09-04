package dev.turtywurty.tutorialmod.init;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.network.BlockPosPayload;
import dev.turtywurty.tutorialmod.screenhandler.ExampleEnergyGeneratorScreenHandler;
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

    public static <T extends ScreenHandler, D extends CustomPayload> ExtendedScreenHandlerType<T, D> register(String name, ExtendedScreenHandlerType.ExtendedFactory<T, D> factory, PacketCodec<? super RegistryByteBuf, D> codec) {
        return Registry.register(Registries.SCREEN_HANDLER, TutorialMod.id(name), new ExtendedScreenHandlerType<>(factory, codec));
    }

    public static void load() {}
}
