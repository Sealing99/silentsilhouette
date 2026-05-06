package net.sealing99.silentsilhouette;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.sealing99.silentsilhouette.block.ModBlocks;
import net.sealing99.silentsilhouette.entity.ModEntities;
import net.sealing99.silentsilhouette.entity.custom.SilhouetteEntity;
import net.sealing99.silentsilhouette.item.ModItemGroups;
import net.sealing99.silentsilhouette.item.ModItems;
import net.sealing99.silentsilhouette.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TheSilentSilhouette implements ModInitializer {
	public static final String MOD_ID = "silentsilhouette";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModEntities.registerModEntities();

		FabricDefaultAttributeRegistry.register(ModEntities.SILHOUETTE, SilhouetteEntity.createAttributes());

		ModWorldGeneration.generateModWorldGen();
	}
}