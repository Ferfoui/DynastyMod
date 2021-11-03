package fr.dynasty.dynastymod.utils.tag;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.block.Block;
import net.minecraft.tags.*;

public class ModBlockTags {

    public static final ITag.INamedTag<Block> CELESTIAL_BLOCKS = tag("celestial_blocks");
    public static final ITag.INamedTag<Block> PALM_LOGS = tag("palm_logs");

    private static ITag.INamedTag<Block> tag(String name) {
        return BlockTags.bind(DynastyMod.rl(name).toString());
    }

}
