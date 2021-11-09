package fr.dynasty.dynastymod.utils;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class ModTags {

    public static class Items {

        public static final ITag.INamedTag<Item> CELESTIAL_BLOCKS = tag("celestial_blocks");
        public static final ITag.INamedTag<Item> INFUSER_ITEMS = tag("infuser_items");
        public static final ITag.INamedTag<Item> PALM_LOGS = tag("palm_logs");

        private static ITag.INamedTag<Item> tag(String name) {
            return ItemTags.bind(DynastyMod.rl(name).toString());
        }
    }

    public static class Blocks {

        public static final ITag.INamedTag<Block> CELESTIAL_BLOCKS = tag("celestial_blocks");
        public static final ITag.INamedTag<Block> PALM_LOGS = tag("palm_logs");

        private static ITag.INamedTag<Block> tag(String name) {
            return BlockTags.bind(DynastyMod.rl(name).toString());
        }
    }
}
