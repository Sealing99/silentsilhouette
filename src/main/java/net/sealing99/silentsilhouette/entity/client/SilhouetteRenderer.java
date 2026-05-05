package net.sealing99.silentsilhouette.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.sealing99.silentsilhouette.TheSilentSilhouette;
import net.sealing99.silentsilhouette.entity.custom.SilhouetteEntity;

public class SilhouetteRenderer extends MobEntityRenderer<SilhouetteEntity, SilhouetteModel<SilhouetteEntity>> {
    public SilhouetteRenderer(EntityRendererFactory.Context context) {
        super(context, new SilhouetteModel<>(context.getPart(SilhouetteModel.SILHOUETTE)), 0.75f);
    }

    @Override
    public Identifier getTexture(SilhouetteEntity entity) {
        return Identifier.of(TheSilentSilhouette.MOD_ID, "textures/entity/silhouette/silhouette.png");
    }

    @Override
    public void render(SilhouetteEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
