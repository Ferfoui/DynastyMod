package fr.dynasty.dynastymod.biome;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModSurfaceBuilders {

    public static final SurfaceBuilderConfig OASIS_CONFIG = new SurfaceBuilderConfig(Blocks.SAND.defaultBlockState(), Blocks.SAND.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> OASIS = register("oasis", SurfaceBuilder.DEFAULT.configured(OASIS_CONFIG));

    private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> configuredSurfaceBuilder) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, DynastyMod.rl(name), configuredSurfaceBuilder);
    }

}
