package fr.dynasty.dynastymod.utils.tag;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.item.Item;
import net.minecraft.tags.*;

public class ModItemTags {

    public static final ITag.INamedTag<Item> CELESTIAL_BLOCKS = tag("celestial_blocks");
    public static final ITag.INamedTag<Item> INFUSER_ITEMS = tag("infuser_items");
    public static final ITag.INamedTag<Item> PALM_LOGS = tag("palm_logs");

    private static ITag.INamedTag<Item> tag(String name) {
        return ItemTags.bind(DynastyMod.rl(name).toString());
    }

}
