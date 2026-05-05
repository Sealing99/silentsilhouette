package net.sealing99.silentsilhouette;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.sealing99.silentsilhouette.entity.ModEntities;
import net.sealing99.silentsilhouette.entity.client.SilhouetteModel;
import net.sealing99.silentsilhouette.entity.client.SilhouetteRenderer;

public class TheSilentSilhouetteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(SilhouetteModel.SILHOUETTE, SilhouetteModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SILHOUETTE, SilhouetteRenderer::new);
    }
}
