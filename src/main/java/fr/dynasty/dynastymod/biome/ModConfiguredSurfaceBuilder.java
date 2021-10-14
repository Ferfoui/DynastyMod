package fr.dynasty.dynastymod.biome;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModConfiguredSurfaceBuilder {
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> OASIS = register("oasis", SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilder.CONFIG_OASIS));

    private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> configuredSurfaceBuilder) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, DynastyMod.rl(name), configuredSurfaceBuilder);
    }
}
