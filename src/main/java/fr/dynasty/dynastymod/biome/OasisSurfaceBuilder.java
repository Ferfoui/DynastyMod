package fr.dynasty.dynastymod.biome;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class OasisSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    public OasisSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    public void apply(Random random, IChunk chunk, Biome biome, int x, int z, int terrainHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (noise > 1.75D) {
            SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, terrainHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.CONFIG_STONE);
        } else if (noise > -0.5D) {
            SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, terrainHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.CONFIG_DESERT);
        } else {
            SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, terrainHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, ModSurfaceBuilder.CONFIG_OASIS);
        }

    }

}
