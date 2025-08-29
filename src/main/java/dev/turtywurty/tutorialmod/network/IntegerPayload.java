package dev.turtywurty.tutorialmod.network;

import dev.turtywurty.tutorialmod.TutorialMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record IntegerPayload(int integer) implements CustomPayload {
    public static final Id<IntegerPayload> ID = new Id<>(TutorialMod.id("integer_payload"));

    public static final PacketCodec<ByteBuf, IntegerPayload> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.INTEGER,
                    IntegerPayload::integer,
                    IntegerPayload::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
