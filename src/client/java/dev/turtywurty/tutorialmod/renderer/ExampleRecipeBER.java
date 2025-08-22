package dev.turtywurty.tutorialmod.renderer;

import dev.turtywurty.tutorialmod.block.entity.ExampleRecipeBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public record ExampleRecipeBER(BlockEntityRendererFactory.Context context)
        implements BlockEntityRenderer<ExampleRecipeBlockEntity> {
    private static final float ROTATION_SPEED = 2f;
    private static final float TOTAL_ROTATIONS = 3.5f;
    private static final float BASE_RADIUS = 5 / 16f;
    private static final float SPIN_PORTION = 0.85f;

    @Override
    public void render(ExampleRecipeBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        matrices.translate(0.5f, 0.0f, 0.5f);
        renderInputItems(entity, matrices, vertexConsumers, light, overlay);
        renderOutputItem(entity, matrices, vertexConsumers, light, overlay);
        matrices.pop();
    }

    private void renderInputItems(ExampleRecipeBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if(entity.getProgress() == 0 || entity.getMaxProgress() == 0 || !(entity.getWorld() instanceof ClientWorld world)) return;

        matrices.push();

        final float inputY = 10.5f/16f;
        final float outputY = 11f/16f;

        float progress = Math.clamp(entity.getProgress() / (float) entity.getMaxProgress(), 0, 1);

        float spinT = Math.clamp((progress / SPIN_PORTION), 0, 1);
        float pullT = Math.clamp((progress - SPIN_PORTION) / (1 - SPIN_PORTION), 0, 1);
        float spinEased = easeInOutCubic(spinT);
        float pullEased = easeOutCubic(pullT);

        float baseAngle = (float) (spinEased * TOTAL_ROTATIONS * Math.TAU);
        float radius = MathHelper.lerp(pullEased, BASE_RADIUS, 0);
        float yOffset = MathHelper.lerp(pullEased, inputY, outputY);
        matrices.translate(0, yOffset, 0);

        SimpleInventory inputInventory = entity.getInputInventory();
        int iterations = Math.max(1, inputInventory.size());
        for (int i = 0; i < iterations; i++) {
            ItemStack stack = inputInventory.getStack(i);
            if (stack.isEmpty()) continue;

            float perItemOffset = (float) (i * (Math.TAU / iterations));
            float rotation = baseAngle + perItemOffset;

            float itemSpin = spinEased * 360 * ROTATION_SPEED;

            matrices.push();
            matrices.multiply(RotationAxis.POSITIVE_Y.rotation(rotation));
            matrices.translate(radius, 0, 0);

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(itemSpin));

            matrices.scale(0.25f, 0.25f, 0.25f);

            spawnParticles(world, entity.getPos(), pullEased, radius, yOffset, -rotation);
            this.context.getItemRenderer().renderItem(stack, ModelTransformationMode.FIXED, light, overlay, matrices, vertexConsumers, entity.getWorld(), i);

            matrices.pop();
        }

        matrices.pop();
    }

    private void spawnParticles(ClientWorld world, BlockPos pos, float pullEased, float radius, float yOffset, float rotation) {
        if(world.getRandom().nextFloat() > 0.125f) return;

        double baseX = pos.getX() + 0.5;
        double baseY = pos.getY() + yOffset;
        double baseZ = pos.getZ() + 0.5;

        double x = baseX + Math.cos(rotation) * radius;
        double z = baseZ + Math.sin(rotation) * radius;

        double tangentX = -Math.sin(rotation);
        double tangentZ = Math.cos(rotation);

        double centreX = (baseX - x);
        double centreZ = (baseZ - z);

        double speed = 0.01;
        double pullBias = 0.6 * pullEased; // more "inward" as it finishes

        double velocityX = tangentX * speed * (1.0 - pullBias) + centreX * 0.02 * pullBias;
        double velocityZ = tangentZ * speed * (1.0 - pullBias) + centreZ * 0.02 * pullBias;
        double velocityY = 0.02 + world.getRandom().nextDouble() * 0.02; // gentle rise

        world.addParticle(ParticleTypes.SMALL_FLAME,
                x, baseY + 0.02, z,
                velocityX, velocityY, velocityZ);
    }

    private void renderOutputItem(ExampleRecipeBlockEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemStack stack = entity.getOutputInventory().getStack(0);
        if (stack.isEmpty()) return;

        float progress = MathHelper.clamp(entity.getProgress() / (float) entity.getMaxProgress(), 0f, 1f);
        float appearT = MathHelper.clamp((progress - SPIN_PORTION) / (1f - SPIN_PORTION), 0f, 1f);
        float appear = easeOutCubic(appearT);

        matrices.push();
        matrices.translate(0, 11/16f + 0.01f, 0);
        float scale = MathHelper.lerp(appear, 0.0f, 0.25f);
        matrices.scale(scale, scale, scale);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
        this.context.getItemRenderer().renderItem(stack, ModelTransformationMode.FIXED,
                light, overlay,
                matrices, vertexConsumers,
                entity.getWorld(), 2);
        matrices.pop();
    }

    private static float easeInOutCubic(float value) {
        return value < 0.5f ? 4f * value * value * value : 1f - (float)Math.pow(-2f * value + 2f, 3f) / 2f;
    }

    private static float easeOutCubic(float value) {
        float u = 1f - value;
        return 1f - u * u * u;
    }
}
