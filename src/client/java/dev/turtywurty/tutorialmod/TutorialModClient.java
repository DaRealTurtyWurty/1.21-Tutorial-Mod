package dev.turtywurty.tutorialmod;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import dev.turtywurty.tutorialmod.init.BlockInit;
import dev.turtywurty.tutorialmod.init.BoatInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TutorialModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Block Render Layers
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BlockInit.EXAMPLE_FLOWER, BlockInit.EXAMPLE_FLOWER_POT,
				BlockInit.EXAMPLE_DOOR, BlockInit.EXAMPLE_SAPLING, BlockInit.EXAMPLE_LEAVES, BlockInit.EXAMPLE_TRAPDOOR);

		// Model Layers
		TerraformBoatClientHelper.registerModelLayers(BoatInit.EXAMPLE_BOAT_ID, false);
	}
}