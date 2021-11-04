package fr.dynasty.dynastymod.world.gen;

import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.world.gen.foliageplacer.PalmFoliagePlacer;
import fr.dynasty.dynastymod.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModFeatures {

    public ConfiguredFeature<?, ?> ORE_AZURITE;
    public ConfiguredFeature<?, ?> ORE_SELENITE;
    public ConfiguredFeature<?, ?> ORE_SOLARITE;
    public ConfiguredFeature<?, ?> FLOWER_PAPYRUS;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> PALM_TREE;

    public void init() {

        ORE_AZURITE = register("ore_azurite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.AZURITE_ORE.get().defaultBlockState(), 3))
                .squared().range(40).count(15));

        ORE_SELENITE = register("ore_selenite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SELENITE_ORE.get().defaultBlockState(), 6))
                .squared().range(20).count(2));

        ORE_SOLARITE = register("ore_solarite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SOLARITE_ORE.get().defaultBlockState(), 6))
                .squared().range(20).count(2));

        PALM_TREE = register("palm", Feature.TREE.configured((
                new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(ModBlocks.PALM_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(ModBlocks.PALM_LEAVES.get().defaultBlockState()),
                        new PalmFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), FeatureSpread.fixed(2)),
                        new StraightTrunkPlacer(5, 2, 2),
                        new TwoLayerFeature(1, 0, 1)))
                .ignoreVines().build()));


        FLOWER_PAPYRUS = register("flower_papyrus", Feature.FLOWER.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.PAPYRUS.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE))
                .tries(64).build()));

    }

    public <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, feature);
    }

    @SubscribeEvent
    public void biomeLoading(final BiomeLoadingEvent e){
        BiomeGenerationSettingsBuilder generation = e.getGeneration();

        if (e.getCategory() != Biome.Category.THEEND) {
            if (e.getCategory() != Biome.Category.NETHER) {
                if (e.getCategory() == Biome.Category.DESERT) {
                    generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_AZURITE);
                    generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FLOWER_PAPYRUS);
                }
                if (e.getCategory() == Biome.Category.SAVANNA) {
                    generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FLOWER_PAPYRUS);
                }

                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_SELENITE);
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_SOLARITE);

            }
        }

        //ModTreeGeneration.generateTrees(e);
    }

}
