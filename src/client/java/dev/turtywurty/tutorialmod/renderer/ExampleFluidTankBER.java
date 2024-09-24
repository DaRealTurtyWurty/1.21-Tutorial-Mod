package dev.turtywurty.tutorialmod.renderer;

import dev.turtywurty.tutorialmod.block.entity.ExampleFluidTankBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;

public class ExampleFluidTankBER implements BlockEntityRenderer<ExampleFluidTankBlockEntity> {
    private final BlockEntityRendererFactory.Context context;

    public ExampleFluidTankBER(BlockEntityRendererFactory.Context context) {
        this.context = context;
    }

    @Override
    public void render(ExampleFluidTankBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        // TODO: Draw fluid quads

        matrices.pop();
    }
}
