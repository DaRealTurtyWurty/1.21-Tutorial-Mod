package dev.turtywurty.tutorialmod.renderer.blockentity;

import dev.turtywurty.tutorialmod.block.entity.ExampleFluidTankBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.SingleFluidStorage;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class ExampleFluidTankBER implements BlockEntityRenderer<ExampleFluidTankBlockEntity> {
    private final BlockEntityRendererFactory.Context context;

    public ExampleFluidTankBER(BlockEntityRendererFactory.Context context) {
        this.context = context;
    }

    @Override
    public void render(ExampleFluidTankBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        SingleFluidStorage fluidTank = entity.getFluidTank();
        if (fluidTank.isResourceBlank() || fluidTank.amount <= 0)
            return;

        FluidVariant fluidVariant = fluidTank.getResource();
        long amount = fluidTank.getAmount();
        long capacity = fluidTank.getCapacity();
        float fillPercentage = (float) amount / capacity;
        fillPercentage = MathHelper.clamp(fillPercentage, 0, 1);

        int color = FluidVariantRendering.getColor(fluidVariant, entity.getWorld(), entity.getPos());
        Sprite sprite = FluidVariantRendering.getSprites(fluidVariant)[0];
        RenderLayer renderLayer = RenderLayers.getFluidLayer(fluidVariant.getFluid().getDefaultState());
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(renderLayer);

        float y1 = 1f / 16f;
        float y2 = ((fillPercentage * 11f) / 16f) + y1;

        float minU = sprite.getFrameU(5f / 16f);
        float maxU = sprite.getFrameU(11f / 16f);
        float minV = sprite.getFrameV(y1);
        float maxV = sprite.getFrameV(y2);

        MatrixStack.Entry entry = matrices.peek();

        // front face
        drawQuad(vertexConsumer, entry, 5f / 16f, y1, 5.001f / 16f, 11f / 16f, y2, 5.001f / 16f, minU, minV, maxU, maxV, color, light, overlay);

        // back face
        drawQuad(vertexConsumer, entry, 11f / 16f, y1, 10.999f / 16f, 5f / 16f, y2, 10.999f / 16f, minU, minV, maxU, maxV, color, light, overlay);

        // left face
        drawQuad(vertexConsumer, entry, 5.001f / 16f, y1, 11f / 16f, 5.001f / 16f, y2, 5f / 16f, minU, minV, maxU, maxV, color, light, overlay);

        // right face
        drawQuad(vertexConsumer, entry, 10.999f / 16f, y1, 5f / 16f, 10.999f / 16f, y2, 11f / 16f, minU, minV, maxU, maxV, color, light, overlay);

        if(fillPercentage < 1f) {
            minU = sprite.getFrameU(5f / 16f);
            maxU = sprite.getFrameU(11f / 16f);
            minV = sprite.getFrameV(5f / 16f);
            maxV = sprite.getFrameV(11f / 16f);

            vertexConsumer.vertex(entry, 5f / 16f, y2, 5f / 16f)
                    .color(color)
                    .texture(minU, maxV)
                    .light(light)
                    .overlay(overlay)
                    .normal(0.0F, 1.0F, 0.0F);

            vertexConsumer.vertex(entry, 5f / 16f, y2, 11f / 16f)
                    .color(color)
                    .texture(minU, minV)
                    .light(light)
                    .overlay(overlay)
                    .normal(0.0F, 1.0F, 0.0F);

            vertexConsumer.vertex(entry, 11f / 16f, y2, 11f / 16f)
                    .color(color)
                    .texture(maxU, minV)
                    .light(light)
                    .overlay(overlay)
                    .normal(0.0F, 1.0F, 0.0F);

            vertexConsumer.vertex(entry, 11f / 16f, y2, 5f / 16f)
                    .color(color)
                    .texture(maxU, maxV)
                    .light(light)
                    .overlay(overlay)
                    .normal(0.0F, 1.0F, 0.0F);
        }
    }

    private static void drawQuad(VertexConsumer vertexConsumer,
                                 MatrixStack.Entry entry,
                                 float x1, float y1, float z1,
                                 float x2, float y2, float z2,
                                 float minU, float minV,
                                 float maxU, float maxV,
                                 int color,
                                 int light, int overlay) {
        vertexConsumer.vertex(entry, x1, y1, z1)
                .color(color)
                .texture(minU, minV)
                .light(light)
                .overlay(overlay)
                .normal(0.0F, 1.0F, 0.0F);

        vertexConsumer.vertex(entry, x1, y2, z1)
                .color(color)
                .texture(minU, maxV)
                .light(light)
                .overlay(overlay)
                .normal(0.0F, 1.0F, 0.0F);

        vertexConsumer.vertex(entry, x2, y2, z2)
                .color(color)
                .texture(maxU, maxV)
                .light(light)
                .overlay(overlay)
                .normal(0.0F, 1.0F, 0.0F);

        vertexConsumer.vertex(entry, x2, y1, z2)
                .color(color)
                .texture(maxU, minV)
                .light(light)
                .overlay(overlay)
                .normal(0.0F, 1.0F, 0.0F);
    }
}
