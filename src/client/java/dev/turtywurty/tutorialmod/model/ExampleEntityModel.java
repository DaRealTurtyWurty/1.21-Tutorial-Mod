package dev.turtywurty.tutorialmod.model;

import dev.turtywurty.tutorialmod.TutorialMod;
import dev.turtywurty.tutorialmod.entity.ExampleEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ExampleEntityModel<T extends ExampleEntity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(TutorialMod.id("example_entity"), "main");
    public static final Identifier TEXTURE_LOCATION = TutorialMod.id("textures/entity/example_entity.png");

    private final ModelPart main;
    private final ModelPart body;
    private final ModelPart lid;
    private final ModelPart handle;
    private final ModelPart legs;
    private final ModelPart frontLegs;
    private final ModelPart frontLeft;
    private final ModelPart leg0;
    private final ModelPart frontRight;
    private final ModelPart leg1;
    private final ModelPart backLegs;
    private final ModelPart backRight;
    private final ModelPart leg2;
    private final ModelPart backLeft;
    private final ModelPart leg3;

    public ExampleEntityModel(ModelPart root) {
        this.main = root.getChild("main");
        this.body = this.main.getChild("body");
        this.lid = this.body.getChild("lid");
        this.handle = this.lid.getChild("handle");
        this.legs = this.main.getChild("legs");
        this.frontLegs = this.legs.getChild("frontLegs");
        this.frontLeft = this.frontLegs.getChild("frontLeft");
        this.leg0 = this.frontLeft.getChild("leg0");
        this.frontRight = this.frontLegs.getChild("frontRight");
        this.leg1 = this.frontRight.getChild("leg1");
        this.backLegs = this.legs.getChild("backLegs");
        this.backRight = this.backLegs.getChild("backRight");
        this.leg2 = this.backRight.getChild("leg2");
        this.backLeft = this.backLegs.getChild("backLeft");
        this.leg3 = this.backLeft.getChild("leg3");
    }

    public static TexturedModelData getTexturedModelData() {
        var modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 11.5F, 0.0F));

        ModelPartData body = main.addChild("body", ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-7.0F, -4.5F, -7.0F, 14.0F, 9.0F, 14.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData lid = body.addChild("lid", ModelPartBuilder.create()
                        .uv(0, 24).cuboid(-7.0F, -5.0F, -14.0F, 14.0F, 5.0F, 14.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -4.5F, 7.0F));

        lid.addChild("handle", ModelPartBuilder.create()
                        .uv(57, 9).cuboid(-4.0F, -1.5F, -1.0F, 1.0F, 2.0F, 2.0F,
                                new Dilation(0.0F))
                        .uv(30, 52).cuboid(3.0F, 0.5F, -2.0F, 2.0F, 1.0F, 4.0F,
                                new Dilation(0.0F))
                        .uv(0, 52).cuboid(-3.0F, -1.5F, -1.0F, 6.0F, 1.0F, 2.0F,
                                new Dilation(0.0F))
                        .uv(9, 56).cuboid(3.0F, -1.5F, -1.0F, 1.0F, 2.0F, 2.0F,
                                new Dilation(0.0F))
                        .uv(17, 52).cuboid(-5.0F, 0.5F, -2.0F, 2.0F, 1.0F, 4.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -6.5F, -7.0F));

        ModelPartData legs = main.addChild("legs", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 12.5F, 0.0F));

        ModelPartData frontLegs = legs.addChild("frontLegs", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, -8.0F, -5.0F));

        ModelPartData frontLeft = frontLegs.addChild("frontLeft", ModelPartBuilder.create().uv(15, 44).cuboid(-1.0F, 6.0F, -2.75F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 0.0F, -0.25F));

        frontLeft.addChild("leg0", ModelPartBuilder.create()
                        .uv(52, 52).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 6.0F, 0.25F));

        ModelPartData frontRight = frontLegs.addChild("frontRight", ModelPartBuilder.create().uv(0, 44).cuboid(-1.0F, 6.0F, -2.75F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 0.0F, -0.25F));

        frontRight.addChild("leg1", ModelPartBuilder.create()
                        .uv(43, 52).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 6.0F, 0.25F));

        ModelPartData backLegs = legs.addChild("backLegs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 5.0F));

        ModelPartData backRight = backLegs.addChild("backRight", ModelPartBuilder.create()
                        .uv(30, 44).cuboid(-1.0F, 6.0F, -2.75F, 2.0F, 2.0F, 5.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(-5.0F, 0.0F, -0.25F));

        backRight.addChild("leg2", ModelPartBuilder.create()
                        .uv(0, 56).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 6.0F, 0.25F));

        ModelPartData backLeft = backLegs.addChild("backLeft", ModelPartBuilder.create()
                        .uv(45, 44).cuboid(-1.0F, 6.0F, -2.75F, 2.0F, 2.0F, 5.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(5.0F, 0.0F, -0.25F));

        backLeft.addChild("leg3", ModelPartBuilder.create()
                        .uv(57, 0).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F,
                                new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 6.0F, 0.25F));

        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.lid.pitch = headPitch * (float) (Math.PI / 180.0);
        this.lid.yaw = netHeadYaw * (float) (Math.PI / 180.0);
        this.backRight.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.backLeft.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontRight.pitch = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.frontLeft.pitch = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        this.main.render(matrices, vertices, light, overlay, color);
    }
}