package dev.turtywurty.tutorialmod;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import dev.turtywurty.tutorialmod.init.BlockEntityTypeInit;
import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.BoatInit;
import dev.turtywurty.tutorialmod.init.ScreenHandlerTypeInit;
import dev.turtywurty.tutorialmod.model.ExampleChestModel;
import dev.turtywurty.tutorialmod.renderer.ExampleInventoryBER;
import dev.turtywurty.tutorialmod.screen.ExampleEnergyGeneratorScreen;
import dev.turtywurty.tutorialmod.screen.ExampleInventoryBlockScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class TutorialModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Block Render Layers
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BlockInit.EXAMPLE_FLOWER, BlockInit.EXAMPLE_FLOWER_POT,
				BlockInit.EXAMPLE_DOOR, BlockInit.EXAMPLE_SAPLING, BlockInit.EXAMPLE_LEAVES, BlockInit.EXAMPLE_TRAPDOOR);

		// Model Layers
		TerraformBoatClientHelper.registerModelLayers(BoatInit.EXAMPLE_BOAT_ID, false);
		EntityModelLayerRegistry.registerModelLayer(ExampleChestModel.LAYER_LOCATION, ExampleChestModel::getTexturedModelData);

		// Bind Screens to Handlers
		HandledScreens.register(ScreenHandlerTypeInit.EXAMPLE_ENERGY_GENERATOR, ExampleEnergyGeneratorScreen::new);
		HandledScreens.register(ScreenHandlerTypeInit.EXAMPLE_INVENTORY_SCREEN_HANDLER, ExampleInventoryBlockScreen::new);

		// Block Entity Renderers
		BlockEntityRendererFactories.register(BlockEntityTypeInit.EXAMPLE_INVENTORY_BLOCK_ENTITY, ExampleInventoryBER::new);
	}
}