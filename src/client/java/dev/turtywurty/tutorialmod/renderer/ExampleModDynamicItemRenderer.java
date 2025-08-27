package dev.turtywurty.tutorialmod.renderer;

import dev.turtywurty.tutorialmod.block.ExampleInventoryBlock;
import dev.turtywurty.tutorialmod.block.entity.ExampleInventoryBlockEntity;
import dev.turtywurty.tutorialmod.init.BlockInit;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class ExampleModDynamicItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    public static final ExampleModDynamicItemRenderer INSTANCE = new ExampleModDynamicItemRenderer();

    private final MinecraftClient client = MinecraftClient.getInstance();

    private final ExampleInventoryBlockEntity exampleInventoryBlockEntity =
            new ExampleInventoryBlockEntity(BlockPos.ORIGIN, BlockInit.EXAMPLE_INVENTORY_BLOCK.getDefaultState());

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Item item = stack.getItem();
        if(item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();

            BlockEntity blockEntity;
            if(block instanceof ExampleInventoryBlock) {
                blockEntity = this.exampleInventoryBlockEntity;
            } else return;

            client.getBlockEntityRenderDispatcher().renderEntity(blockEntity, matrices, vertexConsumers, light, overlay);
        }

        // TODO: Render other items
    }
}
