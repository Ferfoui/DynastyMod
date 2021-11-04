package fr.dynasty.dynastymod.world.gen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModFlowerGeneration {

    public static void generateFlower(final BiomeLoadingEvent e) {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, e.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if (types.contains(BiomeDictionary.Type.SANDY) || e.getCategory() == Biome.Category.SAVANNA) {
            List<Supplier<ConfiguredFeature<?, ?>>> base = e.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            base.add(() -> ModFeatures.FLOWER_PAPYRUS);
        }
    }

}
