package fr.dynasty.dynastymod.utils;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

public class ModTags {

    public static class Items {

        public static final INamedTag<Item> CELESTIAL_BLOCKS = tag("celestial_blocks");
        public static final INamedTag<Item> INFUSER_ITEMS = tag("infuser_items");
        public static final INamedTag<Item> PALM_LOGS = tag("palm_logs");

        private static INamedTag<Item> tag(String name) {
            return ItemTags.bind(DynastyMod.rl(name).toString());
        }

        private static INamedTag<Item> forgeTag(String name) {
            return ItemTags.bind("forge:" + name);
        }
    }

    public static class Blocks {

        public static final INamedTag<Block> CELESTIAL_BLOCKS = tag("celestial_blocks");
        public static final INamedTag<Block> PALM_LOGS = tag("palm_logs");

        private static INamedTag<Block> tag(String name) {
            return BlockTags.bind(DynastyMod.rl(name).toString());
        }

        private static INamedTag<Block> forgeTag(String name) {
            return BlockTags.bind("forge:" + name);
        }
    }
}
