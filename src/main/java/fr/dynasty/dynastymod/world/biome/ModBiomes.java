package fr.dynasty.dynastymod.world.biome;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.world.gen.surfacebuilders.ModConfiguredSurfaceBuilder;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, DynastyMod.MODID);

    public static final RegistryObject<Biome> OASIS = BIOMES.register("oasis", () -> Maker.oasisBiome(0.125f, 0.10f, 1.5f));


    public static class Maker {

        public static Biome oasisBiome(float depth, float scale, float temperature) {
            MobSpawnInfo.Builder mobspawninfo$builder = new MobSpawnInfo.Builder();
            DefaultBiomeFeatures.desertSpawns(mobspawninfo$builder);

            BiomeGenerationSettings.Builder biomegenerationsettings$builder = (new BiomeGenerationSettings.Builder()).surfaceBuilder(ModConfiguredSurfaceBuilder.OASIS);
            DefaultBiomeFeatures.addDefaultOverworldLandStructures(biomegenerationsettings$builder);
            biomegenerationsettings$builder.addStructureStart(StructureFeatures.RUINED_PORTAL_DESERT);
            biomegenerationsettings$builder.addStructureStart(StructureFeatures.VILLAGE_DESERT);
            DefaultBiomeFeatures.addDefaultCarvers(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultLakes(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDesertLakes(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultMonsterRoom(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultUndergroundVariety(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultOres(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultSoftDisks(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultFlowers(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultGrass(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDesertVegetation(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultMushrooms(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDesertExtraVegetation(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDefaultSprings(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addDesertExtraDecoration(biomegenerationsettings$builder);
            DefaultBiomeFeatures.addSurfaceFreezing(biomegenerationsettings$builder);

            return (new Biome.Builder()).precipitation(Biome.RainType.NONE).biomeCategory(Biome.Category.DESERT)
                    .depth(depth).scale(scale)
                    .temperature(temperature)
                    .downfall(0.0f)
                    .specialEffects((new BiomeAmbience.Builder())
                            .waterColor(4159204)
                            .waterFogColor(329011)
                            .fogColor(12638463)
                            .skyColor(calculateSkyColor(temperature))
                            .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                            .build())
                    .mobSpawnSettings(mobspawninfo$builder.build())
                    .generationSettings(biomegenerationsettings$builder.build())
                    .build();
        }

        private static int calculateSkyColor(float temperature) {
            float lvt_1_1_ = temperature / 3.0F;
            lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
            return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
        }
    }
}
