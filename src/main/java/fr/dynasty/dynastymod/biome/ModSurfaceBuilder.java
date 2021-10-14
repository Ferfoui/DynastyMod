package fr.dynasty.dynastymod.biome;

import com.google.common.collect.ImmutableList;
import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilders.*;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSurfaceBuilder {

    private static final BlockState SAND = Blocks.SAND.defaultBlockState();
    private static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
    private static final BlockState DIRT = Blocks.DIRT.defaultBlockState();
    private static final BlockState GRAVEL = Blocks.GRAVEL.defaultBlockState();
    private static final ImmutableList<BlockState> FLOOR_BLOCK_STATES = ImmutableList.of(SAND, GRASS_BLOCK, DIRT);
    private static final ImmutableList<BlockState> CEILING_BLOCK_STATES = ImmutableList.of(SAND, DIRT);


    public static final SurfaceBuilderConfig CONFIG_OASIS = new SurfaceBuilderConfig(GRASS_BLOCK, SAND, GRAVEL);

    /*
    public static final SurfaceBuilder<SurfaceBuilderConfig> OASIS = register("oasis", new OasisSurfaceBuilder(SurfaceBuilderConfig.CODEC));


    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String name, F surfaceBuilder) {
        return ForgeRegistries.SURFACE_BUILDERS.register();
    }*/


}
