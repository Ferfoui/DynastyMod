package fr.dynasty.dynastymod.world.gen.foliageplacer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.dynasty.dynastymod.init.ModTreePlacer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder
            .create((trunk_height) -> foliagePlacerParts(trunk_height).and(FeatureSpread.codec(0, 16, 8).fieldOf("trunk_height")
                    .forGetter((palmFoliage) -> palmFoliage.trunkHeight)).apply(trunk_height, PalmFoliagePlacer::new));

    private final FeatureSpread trunkHeight;

    public PalmFoliagePlacer(FeatureSpread radius, FeatureSpread offset, FeatureSpread trunkHeight) {
        super(radius, offset);
        this.trunkHeight = trunkHeight;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModTreePlacer.FoliageType.PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config, int trunkHeight, Foliage foliage, int foliageBottomHeight, int radius, Set<BlockPos> leaves, int foliageTopHeight, MutableBoundingBox boundingBox) {
        BlockPos blockpos = foliage.foliagePos();

        this.placeLeavesRow(reader, random, config, blockpos, 1, leaves, foliageTopHeight, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos, 2, leaves, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);

        this.placeLeavesRow(reader, random, config, blockpos.west(2), 1, leaves, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.north(2), 1, leaves, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.east(2), 1, leaves, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.south(2), 1, leaves, foliageTopHeight-1, foliage.doubleTrunk(), boundingBox);

        this.placeLeavesRow(reader, random, config, blockpos.west(3), 1, leaves, foliageTopHeight-2, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.north(3), 1, leaves, foliageTopHeight-2, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.east(3), 1, leaves, foliageTopHeight-2, foliage.doubleTrunk(), boundingBox);
        this.placeLeavesRow(reader, random, config, blockpos.south(3), 1, leaves, foliageTopHeight-2, foliage.doubleTrunk(), boundingBox);
    }

    @Override
    public int foliageHeight(Random random, int trunkHeight, BaseTreeFeatureConfig config) {
        return Math.max(4, trunkHeight - this.trunkHeight.sample(random));
    }

    @Override
    protected boolean shouldSkipLocation(Random random, int baseHeight, int dx, int dy, int dz, boolean bl) {
        return baseHeight == dz && dy == dz && dz > 0;
    }

}
