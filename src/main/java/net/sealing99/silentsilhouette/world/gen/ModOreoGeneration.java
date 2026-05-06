package net.sealing99.silentsilhouette.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.sealing99.silentsilhouette.world.ModPlacedFeatures;

public class ModOreoGeneration {
    public static void generateOreos() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.PINK_GARNET_ORE_SMALL_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.PINK_GARNET_ORE_BIG_PLACED_KEY);
    }
}
