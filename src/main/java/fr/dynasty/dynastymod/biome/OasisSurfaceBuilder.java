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
    public void apply(Random p_205610_1_, IChunk p_205610_2_, Biome p_205610_3_, int p_205610_4_, int p_205610_5_, int p_205610_6_, double p_205610_7_, BlockState p_205610_9_, BlockState p_205610_10_, int p_205610_11_, long p_205610_12_, SurfaceBuilderConfig p_205610_14_) {
        if (p_205610_7_ > 1.75D) {
            SurfaceBuilder.DEFAULT.apply(p_205610_1_, p_205610_2_, p_205610_3_, p_205610_4_, p_205610_5_, p_205610_6_, p_205610_7_, p_205610_9_, p_205610_10_, p_205610_11_, p_205610_12_, SurfaceBuilder.CONFIG_STONE);
        } else if (p_205610_7_ > -0.5D) {
            SurfaceBuilder.DEFAULT.apply(p_205610_1_, p_205610_2_, p_205610_3_, p_205610_4_, p_205610_5_, p_205610_6_, p_205610_7_, p_205610_9_, p_205610_10_, p_205610_11_, p_205610_12_, SurfaceBuilder.CONFIG_DESERT);
        } else {
            SurfaceBuilder.DEFAULT.apply(p_205610_1_, p_205610_2_, p_205610_3_, p_205610_4_, p_205610_5_, p_205610_6_, p_205610_7_, p_205610_9_, p_205610_10_, p_205610_11_, p_205610_12_, ModSurfaceBuilder.CONFIG_OASIS);
        }

    }

}
