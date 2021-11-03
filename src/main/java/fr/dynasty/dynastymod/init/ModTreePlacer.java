package fr.dynasty.dynastymod.init;

import com.mojang.serialization.Codec;
import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.world.gen.foliageplacer.PalmFoliagePlacer;
import fr.dynasty.dynastymod.world.gen.trunkplacer.RootedTrunkPlacer;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTreePlacer {
    public static class FoliageType {
        public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, DynastyMod.MODID);

        public static final RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));
    }

    public static class TrunkType {
        public static final TrunkPlacerType<RootedTrunkPlacer> PALM_TRUNK_PLACER = register("palm_trunk_placer", RootedTrunkPlacer.CODEC);

        private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
            return Registry.register(Registry.TRUNK_PLACER_TYPES, DynastyMod.rl(name) , new TrunkPlacerType<>(codec));
        }
    }
}
