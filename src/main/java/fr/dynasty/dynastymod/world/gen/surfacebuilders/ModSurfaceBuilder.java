package fr.dynasty.dynastymod.world.gen.surfacebuilders;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSurfaceBuilder {

    public static class Config {
        private static final BlockState SAND = Blocks.SAND.defaultBlockState();
        private static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
        private static final BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();

        public static final SurfaceBuilderConfig OASIS = new SurfaceBuilderConfig(GRASS_BLOCK, SAND, GRAVEL);
    }
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, DynastyMod.MODID);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> OASIS = SURFACE_BUILDERS.register("oasis", () -> new OasisSurfaceBuilder(SurfaceBuilderConfig.CODEC));
}
