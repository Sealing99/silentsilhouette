package net.sealing99.silentsilhouette.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.sealing99.silentsilhouette.TheSilentSilhouette;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TheSilentSilhouette.MOD_ID, name));
        }
    }

    public static class Items {
        /**
         * Example of an item tag:
         * <p> {@code public static final TagKey<Item> TAG_KEY = createTag("tag_key"); } </p>
         */

        public static final TagKey<Item> TAG_KEY = createTag("tag_key");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TheSilentSilhouette.MOD_ID, name));
        }
    }
}
