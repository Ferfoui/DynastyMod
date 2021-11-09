package fr.dynasty.dynastymod.world.gen;

import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.world.gen.foliageplacer.PalmFoliagePlacer;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class ModFeatures {

    public static ConfiguredFeature<?, ?> FLOWER_PAPYRUS;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> PALM_TREE;

    public void init() {

        PALM_TREE = register("palm", Feature.TREE.configured((
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(ModBlocks.PALM_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(ModBlocks.PALM_LEAVES.get().defaultBlockState()),
                        new PalmFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(5, 3, 2),
                        new TwoLayerFeature(1, 0, 1)))
                .ignoreVines().build()));


        FLOWER_PAPYRUS = Feature.FLOWER.configured((
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.PAPYRUS.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE))
                        .tries(12).build())
                .decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1);

    }

    public <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, feature);
    }
}
