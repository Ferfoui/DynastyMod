package fr.dynasty.dynastymod.world.gen;

import fr.dynasty.dynastymod.world.structure.ModStructures;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ModStructureGeneration {
    public static void generateStructures(final BiomeLoadingEvent e) {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, e.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if(types.contains(BiomeDictionary.Type.SANDY)) {
            List<Supplier<StructureFeature<?, ?>>> structures = e.getGeneration().getStructures();

            structures.add(() -> ModStructures.OASIS.get().configured(IFeatureConfig.NONE));
        }
    }
}
