package dev.turtywurty.tutorialmod.renderer;

import dev.turtywurty.tutorialmod.block.ExampleInventoryBlock;
import dev.turtywurty.tutorialmod.block.entity.ExampleInventoryBlockEntity;
import dev.turtywurty.tutorialmod.model.ExampleChestModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class ExampleInventoryBER implements BlockEntityRenderer<ExampleInventoryBlockEntity> {
    private final ExampleChestModel model;

    public ExampleInventoryBER(BlockEntityRendererFactory.Context context) {
        this.model = new ExampleChestModel(context.getLayerModelPart(ExampleChestModel.LAYER_LOCATION));
    }

    @Override
    public void render(ExampleInventoryBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5f, 0.0f, 0.5f);

        int numPlayersOpen = entity.getNumPlayersOpen();
        float lidAngle = entity.lidAngle;

        ModelPart lid = this.model.getLid();
        float defaultLidAngle = lid.pitch;

        double maxAngle = Math.toRadians(110);

        if (numPlayersOpen > 0 && lidAngle < maxAngle) {
            lid.pitch = MathHelper.lerp(tickDelta / 16, lidAngle, (float) maxAngle);
        } else if (numPlayersOpen == 0 && lidAngle > defaultLidAngle) {
            lid.pitch = MathHelper.lerp(tickDelta / 16, lidAngle, defaultLidAngle);
        }

        entity.lidAngle = lid.pitch;

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(switch (entity.getCachedState().get(ExampleInventoryBlock.FACING)) {
            case EAST -> 270;
            case SOUTH -> 180;
            case WEST -> 90;
            default -> 0;
        }));

        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(ExampleChestModel.TEXTURE_LOCATION)), light, overlay);

        lid.pitch = defaultLidAngle;

        matrices.pop();
    }
}
