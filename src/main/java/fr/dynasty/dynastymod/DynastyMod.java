package fr.dynasty.dynastymod;

import com.google.common.collect.ImmutableMap;
import fr.dynasty.dynastymod.blocks.ModWoodTypes;
import fr.dynasty.dynastymod.world.ModWorldEvents;
import fr.dynasty.dynastymod.world.biome.ModBiomes;
import fr.dynasty.dynastymod.world.gen.ModBiomeGeneration;
import fr.dynasty.dynastymod.world.gen.surfacebuilders.ModSurfaceBuilder;
import fr.dynasty.dynastymod.blocks.soulinfuser.SoulInfuserScreen;
import fr.dynasty.dynastymod.init.*;
import fr.dynasty.dynastymod.init.ModRecipes;
import fr.dynasty.dynastymod.world.structure.ModStructures;
import net.minecraft.block.Block;
import net.minecraft.block.WoodType;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.AxeItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DynastyMod.MODID)
public class DynastyMod {

    //modid
    public static final String MODID = "dynastymod";

    //Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    //resourceLocation of mod
    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MODID, path);
    }

    //network
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(rl("channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);


    public DynastyMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);

        //registration
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModTileEntities.TILE_ENTITIES.register(bus);
        ModContainers.CONTAINERS.register(bus);
        ModRecipes.register(bus);
        ModBiomes.BIOMES.register(bus);
        ModTreePlacer.FoliageType.FOLIAGE_PLACERS.register(bus);
        ModSurfaceBuilder.SURFACE_BUILDERS.register(bus);
        ModStructures.STRUCTURES.register(bus);

    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                    .put(ModBlocks.PALM_LOG.get(), ModBlocks.STRIPPED_PALM_LOG.get())
                    .put(ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_WOOD.get()).build();


            ModWorldEvents.initFeatures();
            ModStructures.setupStructures();

            WoodType.register(ModWoodTypes.PALM);

            ModBiomeGeneration.generateBiome();

        });

        //network
        /*int networkIndex = 0;
        NETWORK.registerMessage(networkIndex)*/

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.PAPYRUS.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.PALM_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.PALM_SAPLING.get(), RenderType.cutout());

            //screen
            ScreenManager.register(ModContainers.SOUL_INFUSER_CONTAINER.get(), SoulInfuserScreen::new);

            //key
            ModKeyBindings.register();
        });
    }

    //when the server start
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }
}
