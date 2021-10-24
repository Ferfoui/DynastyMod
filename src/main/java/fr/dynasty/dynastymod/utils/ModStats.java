package fr.dynasty.dynastymod.utils;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class ModStats extends Stats {


    public static final ResourceLocation INTERACT_WITH_SOUL_INFUSER = makeCustomStat("interact_with_soul_infuser", IStatFormatter.DEFAULT);

    private static ResourceLocation makeCustomStat(String path, IStatFormatter statFormatter) {
        ResourceLocation resourcelocation = DynastyMod.rl(path);
        Registry.register(Registry.CUSTOM_STAT, path, resourcelocation);
        CUSTOM.get(resourcelocation, statFormatter);
        return resourcelocation;
    }
}
