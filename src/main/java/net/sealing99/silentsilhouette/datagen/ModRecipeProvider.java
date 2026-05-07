package net.sealing99.silentsilhouette.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.sealing99.silentsilhouette.TheSilentSilhouette;
import net.sealing99.silentsilhouette.block.ModBlocks;
import net.sealing99.silentsilhouette.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> PINK_GARNET_SMELTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE);

        offerSmelting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET_SHARD, 0.25f, 200, "pink_garnet_smelt");
        offerBlasting(exporter, PINK_GARNET_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET_SHARD, 0.25f, 200, "pink_garnet_blast");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.MISC, ModBlocks.PINK_GARNET_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_PINK_GARNET, RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.PINK_GARNET_SHARD, RecipeCategory.MISC, ModItems.PINK_GARNET, "pink_garnet_from_shard", null, "pink_garnet_shard_from_normal", null);

        offerSmithingTrimRecipe(exporter, ModItems.SEAL_SMITHING_TEMPLATE, Identifier.of(TheSilentSilhouette.MOD_ID, "seal"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_GARNET_CRUCIFIX, 1)
                .pattern(" G ")
                .pattern("GGG")
                .pattern(" R ")
                .input('G', ModItems.PINK_GARNET)
                .input('R', Items.BLAZE_ROD)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, Identifier.of(TheSilentSilhouette.MOD_ID, "pink_garnet_crucifix"));
    }
}
