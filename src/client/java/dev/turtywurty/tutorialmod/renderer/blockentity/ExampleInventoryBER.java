package dev.turtywurty.tutorialmod.renderer.blockentity;

import dev.turtywurty.tutorialmod.block.ExampleInventoryBlock;
import dev.turtywurty.tutorialmod.block.entity.ExampleInventoryBlockEntity;
import dev.turtywurty.tutorialmod.model.ExampleChestModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ExampleInventoryBER implements BlockEntityRenderer<ExampleInventoryBlockEntity> {
    private static final List<ItemTransformation> TRANSFORMATIONS = new ArrayList<>();

    static {
        Random random = ThreadLocalRandom.current();
        for (int index = 0; index < 36; index++) {
            TRANSFORMATIONS.add(new ItemTransformation(
                    (random.nextDouble() - 0.5d) * 0.4375D,
                    (random.nextDouble() - 0.5d) * 0.4375D,
                    random.nextInt(360))
            );
        }
    }

    private final BlockEntityRendererFactory.Context context;
    private final ExampleChestModel model;

    public ExampleInventoryBER(BlockEntityRendererFactory.Context context) {
        this.context = context;
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

        if(entity.lidAngle > 0.1D) {
            SimpleInventory inventory = entity.getInventory();
            World world = entity.getWorld();

            for (int index = 0; index < inventory.getHeldStacks().size(); index++) {
                ItemStack stack = inventory.getStack(index);
                if(stack.isEmpty()) continue;

                ItemTransformation transformation = TRANSFORMATIONS.get(index);

                matrices.push();
                matrices.translate(transformation.x(), 0.5D, transformation.z());
                matrices.scale(0.325f, 0.325f, 0.325f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(transformation.rotation()));

                this.context.getItemRenderer().renderItem(stack, ModelTransformationMode.FIXED,
                        light, overlay,
                        matrices, vertexConsumers,
                        world, 0);

                matrices.pop();
            }
        }

        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(ExampleChestModel.TEXTURE_LOCATION)), light, overlay);

        lid.pitch = defaultLidAngle;

        matrices.pop();
    }

    public record ItemTransformation(double x, double z, int rotation) {}
}
