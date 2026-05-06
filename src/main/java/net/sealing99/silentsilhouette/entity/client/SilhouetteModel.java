package net.sealing99.silentsilhouette.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.sealing99.silentsilhouette.TheSilentSilhouette;
import net.sealing99.silentsilhouette.entity.custom.SilhouetteEntity;

public class SilhouetteModel<T extends SilhouetteEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer SILHOUETTE = new EntityModelLayer(Identifier.of(TheSilentSilhouette.MOD_ID, "silhouette"), "main");

    private final ModelPart root;
    private final ModelPart head;

    public SilhouetteModel(ModelPart root) {
        this.root = root.getChild("root");
        this.head = this.root.getChild("head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        ModelPartData torso = root.addChild("torso", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(24, 0).cuboid(-4.0F, -8.0F, -5.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 1.0F));

        ModelPartData body = torso.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -5.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -19.0F, 0.0F));

        ModelPartData rightArm = root.addChild("rightArm", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -3.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -22.0F, 1.0F));

        ModelPartData leftArm = root.addChild("leftArm", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, -3.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -22.0F, 1.0F));

        ModelPartData rightLeg = root.addChild("rightLeg", ModelPartBuilder.create().uv(0, 0).cuboid(2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -11.0F, 0.0F));

        ModelPartData leftLeg = root.addChild("leftLeg", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -11.0F, 0.0F));
        return TexturedModelData.of(modelData, 56, 32);
    }
    @Override
    public void setAngles(SilhouetteEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(SilhouetteAnimations.ANIM_SILHOUETTE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, SilhouetteAnimations.ANIM_SILHOUETTE_IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw   = MathHelper.clamp(headYaw  , -90.0f, 90.0f);
        headPitch = MathHelper.clamp(headPitch, -90.0f, 90.0f);

        this.head.yaw   = headYaw   * 0.017453292f;
        this.head.pitch = headPitch * -0.017453292f;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}