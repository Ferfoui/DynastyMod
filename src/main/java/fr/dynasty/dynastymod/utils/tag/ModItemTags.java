package fr.dynasty.dynastymod.utils.tag;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.item.Item;
import net.minecraft.tags.*;

public class ModItemTags {


    public static final ITag.INamedTag<Item> SELENITE_ORES = tag("selenite_ores");
    public static final ITag.INamedTag<Item> CELESTIAL_BLOCKS = tag("celestial_blocks");


    private static ITag.INamedTag<Item> tag(String name) {
        return ItemTags.bind(DynastyMod.rl(name).toString());
    }

}
