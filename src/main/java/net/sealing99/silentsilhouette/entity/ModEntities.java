package net.sealing99.silentsilhouette.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.sealing99.silentsilhouette.TheSilentSilhouette;
import net.sealing99.silentsilhouette.entity.custom.SilhouetteEntity;

public class ModEntities {
    public static final EntityType<SilhouetteEntity> SILHOUETTE = Registry.register(
        Registries.ENTITY_TYPE,
        Identifier.of(TheSilentSilhouette.MOD_ID, "silhouette"),
        EntityType.Builder.create(SilhouetteEntity::new, SpawnGroup.MONSTER)
                .dimensions(0.6f, 2.0f).build()
    );

    public static void registerModEntities() {
        TheSilentSilhouette.LOGGER.info("Registering Mod Entities for " + TheSilentSilhouette.MOD_ID);
    }
}
