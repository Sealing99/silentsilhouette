package net.sealing99.silentsilhouette.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.sealing99.silentsilhouette.item.ModItems;


import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS).add(ModItems.PINK_GARNET);
        getOrCreateTagBuilder(ItemTags.TRIM_TEMPLATES).add(ModItems.SEAL_SMITHING_TEMPLATE);
    }
}
