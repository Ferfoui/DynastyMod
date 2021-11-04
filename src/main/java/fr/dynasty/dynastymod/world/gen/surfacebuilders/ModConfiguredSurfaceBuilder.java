package fr.dynasty.dynastymod.world.gen.surfacebuilders;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModConfiguredSurfaceBuilder {
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> OASIS = register("oasis_surface", SurfaceBuilder.DEFAULT.configured(ModSurfaceBuilder.Config.OASIS));

    private static <SC extends ISurfaceBuilderConfig> ConfiguredSurfaceBuilder<SC> register(String name, ConfiguredSurfaceBuilder<SC> configuredSurfaceBuilder) {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, DynastyMod.rl(name), configuredSurfaceBuilder);
    }
}
