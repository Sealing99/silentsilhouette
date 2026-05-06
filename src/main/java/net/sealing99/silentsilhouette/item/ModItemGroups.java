package net.sealing99.silentsilhouette.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.sealing99.silentsilhouette.TheSilentSilhouette;
import net.sealing99.silentsilhouette.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup SILENT_SILHOUETTE_GROUP = Registry.register(
        Registries.ITEM_GROUP,
            Identifier.of(TheSilentSilhouette.MOD_ID, "silent_silhouette_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.silentsilhouette.silent_silhouette_group"))
                    .icon(() -> new ItemStack(ModItems.SILHOUETTE_HEART))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.SILHOUETTE_HEART);
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);
                        entries.add(ModItems.PINK_GARNET_SHARD);
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.PINK_GARNET_ORE);
                        entries.add(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
                        entries.add(ModItems.SILHOUETTE_SPAWN_EGG);
                    })

                    .build()
    );

    public static void registerItemGroups() {
        TheSilentSilhouette.LOGGER.info("Registering Item Groups for " + TheSilentSilhouette.MOD_ID);
    }
}
