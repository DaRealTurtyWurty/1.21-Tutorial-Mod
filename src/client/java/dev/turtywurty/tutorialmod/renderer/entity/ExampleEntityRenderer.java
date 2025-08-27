package dev.turtywurty.tutorialmod.renderer.entity;

import dev.turtywurty.tutorialmod.entity.ExampleEntity;
import dev.turtywurty.tutorialmod.model.ExampleEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class ExampleEntityRenderer extends MobEntityRenderer<ExampleEntity, ExampleEntityModel<ExampleEntity>> {
    public ExampleEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ExampleEntityModel<>(context.getPart(ExampleEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTexture(ExampleEntity entity) {
        return ExampleEntityModel.TEXTURE_LOCATION;
    }
}
