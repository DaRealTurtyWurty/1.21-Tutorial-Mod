package dev.turtywurty.tutorialmod;

import dev.turtywurty.tutorialmod.init.BlockInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TutorialModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BlockInit.EXAMPLE_FLOWER, BlockInit.EXAMPLE_FLOWER_POT);
	}
}