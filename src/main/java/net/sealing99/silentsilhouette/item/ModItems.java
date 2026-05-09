package net.sealing99.silentsilhouette.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.trim.ArmorTrimPattern;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;
import net.sealing99.silentsilhouette.TheSilentSilhouette;
import net.sealing99.silentsilhouette.entity.ModEntities;
import net.sealing99.silentsilhouette.trim.ModTrimPatterns;

public class ModItems {
    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", new Item(new Item.Settings()));
    public static final Item PINK_GARNET_SHARD = registerItem("pink_garnet_shard", new Item(new Item.Settings()));

    public static final Item SILHOUETTE_HEART = registerItem("silhouette_heart", new Item(new Item.Settings()));

    public static final Item SILHOUETTE_SPAWN_EGG = registerItem("silhouette_spawn_egg", new SpawnEggItem(ModEntities.SILHOUETTE, 0x9dc783, 0xbfaf5f, new Item.Settings()));

    public static final Item SEAL_SMITHING_TEMPLATE = registerItem("seal_armor_trim_smithing_template", SmithingTemplateItem.of(Identifier.of(TheSilentSilhouette.MOD_ID, "seal"), FeatureFlags.VANILLA));

    public static final Item PINK_GARNET_CRUCIFIX = registerItem("pink_garnet_crucifix", new Item(new Item.Settings()));

    public static final Item URANIUM_SANDWICH = registerItem("uranium_sandwich", new Item(new Item.Settings().food(ModFoodComponents.URANIUM_SANDWICH)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TheSilentSilhouette.MOD_ID, name), item);
    }

    public static void registerModItems() {
        TheSilentSilhouette.LOGGER.info("Registering Mod Items for " + TheSilentSilhouette.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(RAW_PINK_GARNET);
        });
    }
}
