package fr.dynasty.dynastymod.world;

import com.mojang.serialization.Codec;
import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.world.gen.*;
import fr.dynasty.dynastymod.world.structure.ModStructures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = DynastyMod.MODID)
public class ModWorldEvents {

    public static void initFeatures() {
        new ModFeatures().init();
    }

    @SubscribeEvent
    public static void biomeLoading(final BiomeLoadingEvent event){
        ModStructureGeneration.generateStructures(event);

        ModOreGeneration.generateOres(event);
        ModFlowerGeneration.generateFlower(event);
        ModTreeGeneration.generateTrees(event);

        ModEntityGeneration.onEntitySpawn(event);
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            try {
                Method GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>)GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));

                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) {
                    return;
                }
            } catch (Exception e) {
                DynastyMod.LOGGER.error("Was unable to check if " + serverWorld.dimension().location() + " is using Terraforged's ChunkGenerator.");
            }

            // Prevent spawning our structure in Vanilla's superflat world
            if (serverWorld.getChunkSource().generator instanceof FlatChunkGenerator && serverWorld.dimension().equals(World.OVERWORLD)) {
                return;
            }

            // Adding our Structure to the Map
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
            addStructures(tempMap);

            serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;

        }
    }

    private static void addStructures(Map<Structure<?>, StructureSeparationSettings> tempMap) {
        tempMap.putIfAbsent(ModStructures.OASIS.get(), DimensionStructuresSettings.DEFAULTS.get(ModStructures.OASIS.get()));
    }
}
