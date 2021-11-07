package fr.dynasty.dynastymod.world.gen.trunkplacer;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.dynasty.dynastymod.init.ModTreePlacer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RootedTrunkPlacer extends AbstractTrunkPlacer {
    public static final Codec<RootedTrunkPlacer> CODEC = RecordCodecBuilder.create((me) -> trunkPlacerParts(me).apply(me, RootedTrunkPlacer::new));

    public RootedTrunkPlacer(int baseHeight, int firstRandHeight, int secondRandHeight) {
        super(baseHeight, firstRandHeight, secondRandHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTreePlacer.TrunkType.ROOTED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.Foliage> placeTrunk(IWorldGenerationReader world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> set, MutableBoundingBox blockBox, BaseTreeFeatureConfig config) {
        int treeBaseHeight = config.trunkPlacer.getTreeHeight(random);

        int rootHeight = random.nextInt(2) + MathHelper.floor(treeBaseHeight / 5) + 1;
        int rootSplay = 2;
        BlockPos pos1 = pos;

        if (!config.fromSapling) {
            setDirtAt(world, pos.below());
        }
        for (int i = 0; i < treeBaseHeight; i++) {
            if (i < rootHeight) {
                placeLog(world, random, pos1.west(rootSplay), set, blockBox, config);
                placeLog(world, random, pos1.north(rootSplay), set, blockBox, config);
                placeLog(world, random, pos1.east(rootSplay), set, blockBox, config);
                placeLog(world, random, pos1.south(rootSplay), set, blockBox, config);
            } else if (rootSplay > 0) {
                rootSplay--;
                placeLog(world, random, pos1.west(rootSplay), set, blockBox, config);
                placeLog(world, random, pos1.north(rootSplay), set, blockBox, config);
                placeLog(world, random, pos1.east(rootSplay), set, blockBox, config);
                placeLog(world, random, pos1.south(rootSplay), set, blockBox, config);
            } else {
                placeLog(world, random, pos1, set, blockBox, config);
            }
            pos1 = pos1.above(1);
        }

        return ImmutableList.of(new FoliagePlacer.Foliage(pos.above(treeBaseHeight), 0, false));
    }
}
