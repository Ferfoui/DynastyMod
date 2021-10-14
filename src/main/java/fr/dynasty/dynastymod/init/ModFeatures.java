package fr.dynasty.dynastymod.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModFeatures {

    public ConfiguredFeature<?, ?> ORE_AZURITE_FEATURE;
    public ConfiguredFeature<?, ?> ORE_SELENITE_FEATURE;
    public ConfiguredFeature<?, ?> ORE_SOLARITE_FEATURE;
    public ConfiguredFeature<?, ?> FLOWER_PAPYRUS_FEATURE;

    public void init() {

        ORE_AZURITE_FEATURE = register("ore_azurite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.AZURITE_ORE.get().defaultBlockState(), 3))
                .squared()
                .range(40)
                .count(15));

        ORE_SELENITE_FEATURE = register("ore_selenite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SELENITE_ORE.get().defaultBlockState(), 6))
                .squared()
                .range(20)
                .count(2));

        ORE_SOLARITE_FEATURE = register("ore_solarite", Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.SOLARITE_ORE.get().defaultBlockState(), 6))
                .squared()
                .range(20)
                .count(2));

        FLOWER_PAPYRUS_FEATURE = register("flower_papyrus", Feature.FLOWER.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.PAPYRUS.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE))
                .tries(64).build()));

    }

    public <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> feature) {

        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, name, feature);
    }

    @SubscribeEvent
    public void biomeLoading(BiomeLoadingEvent e){
        BiomeGenerationSettingsBuilder generation = e.getGeneration();

        if (e.getCategory() != Biome.Category.THEEND) {
            if (e.getCategory() != Biome.Category.NETHER) {
                if (e.getCategory() == Biome.Category.DESERT) {
                    generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_AZURITE_FEATURE);
                    generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FLOWER_PAPYRUS_FEATURE);
                }
                if (e.getCategory() == Biome.Category.SAVANNA) {
                    generation.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FLOWER_PAPYRUS_FEATURE);
                }

                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_SELENITE_FEATURE);
                generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ORE_SOLARITE_FEATURE);

            }
        }
    }

}
