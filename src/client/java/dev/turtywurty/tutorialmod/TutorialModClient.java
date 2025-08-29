package dev.turtywurty.tutorialmod;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import dev.turtywurty.tutorialmod.init.*;
import dev.turtywurty.tutorialmod.model.ExampleChestModel;
import dev.turtywurty.tutorialmod.model.ExampleEntityModel;
import dev.turtywurty.tutorialmod.renderer.blockentity.ExampleFluidTankBER;
import dev.turtywurty.tutorialmod.renderer.blockentity.ExampleInventoryBER;
import dev.turtywurty.tutorialmod.renderer.blockentity.ExampleRecipeBER;
import dev.turtywurty.tutorialmod.renderer.entity.ExampleEntityRenderer;
import dev.turtywurty.tutorialmod.renderer.item.ExampleModDynamicItemRenderer;
import dev.turtywurty.tutorialmod.screen.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Block Render Layers
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BlockInit.EXAMPLE_FLOWER, BlockInit.EXAMPLE_FLOWER_POT,
                BlockInit.EXAMPLE_DOOR, BlockInit.EXAMPLE_SAPLING, BlockInit.EXAMPLE_LEAVES, BlockInit.EXAMPLE_TRAPDOOR,
                BlockInit.EXAMPLE_FLUID_TANK);

        // Model Layers
        TerraformBoatClientHelper.registerModelLayers(BoatInit.EXAMPLE_BOAT_ID, false);
        EntityModelLayerRegistry.registerModelLayer(ExampleChestModel.LAYER_LOCATION, ExampleChestModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ExampleEntityModel.LAYER_LOCATION, ExampleEntityModel::getTexturedModelData);

        // Bind Screens to Handlers
        HandledScreens.register(ScreenHandlerTypeInit.EXAMPLE_ENERGY_GENERATOR, ExampleEnergyGeneratorScreen::new);
        HandledScreens.register(ScreenHandlerTypeInit.EXAMPLE_INVENTORY_SCREEN_HANDLER, ExampleInventoryBlockScreen::new);
        HandledScreens.register(ScreenHandlerTypeInit.EXAMPLE_FLUID_TANK, ExampleFluidTankScreen::new);
        HandledScreens.register(ScreenHandlerTypeInit.EXAMPLE_RECIPE_SCREEN_HANDLER, ExampleRecipeScreen::new);
        HandledScreens.register(ScreenHandlerTypeInit.EXAMPLE_ENTITY, ExampleEntityScreen::new);

        // Block Entity Renderers
        BlockEntityRendererFactories.register(BlockEntityTypeInit.EXAMPLE_INVENTORY_BLOCK_ENTITY, ExampleInventoryBER::new);
        BlockEntityRendererFactories.register(BlockEntityTypeInit.EXAMPLE_FLUID_TANK, ExampleFluidTankBER::new);
        BlockEntityRendererFactories.register(BlockEntityTypeInit.EXAMPLE_RECIPE_BLOCK_ENTITY, ExampleRecipeBER::new);

        // Dynamic Item Renderers
        BuiltinItemRendererRegistry.INSTANCE.register(BlockInit.EXAMPLE_INVENTORY_BLOCK, ExampleModDynamicItemRenderer.INSTANCE);

        // Entity Renderers
        EntityRendererRegistry.register(EntityTypeInit.EXAMPLE_ENTITY, ExampleEntityRenderer::new);
    }
}