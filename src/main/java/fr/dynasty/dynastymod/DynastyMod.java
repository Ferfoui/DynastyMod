package fr.dynasty.dynastymod;

import com.google.common.collect.ImmutableMap;
import fr.dynasty.dynastymod.world.biome.ModBiomes;
import fr.dynasty.dynastymod.world.gen.surfacebuilders.ModSurfaceBuilder;
import fr.dynasty.dynastymod.blocks.soulinfuser.SoulInfuserScreen;
import fr.dynasty.dynastymod.init.*;
import fr.dynasty.dynastymod.init.ModRecipes;
import fr.dynasty.dynastymod.world.gen.ModFeatures;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.AxeItem;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod(DynastyMod.MODID)
public class DynastyMod {

    //modid
    public static final String MODID = "dynastymod";

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

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);

        //registration
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModTileEntities.TILE_ENTITIES.register(bus);
        ModContainers.CONTAINERS.register(bus);
        ModRecipes.register(bus);
        ModBiomes.BIOMES.register(bus);
        ModTreePlacer.FoliageType.FOLIAGE_PLACERS.register(bus);
        ModSurfaceBuilder.SURFACE_BUILDERS.register(bus);

        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(RegistryKey.create(Registry.BIOME_REGISTRY, rl("oasis")), 5));

    }

    private void setup(FMLCommonSetupEvent e) {
        e.enqueueWork(() -> {
            AxeItem.STRIPABLES = new ImmutableMap.Builder<Block, Block>().putAll(AxeItem.STRIPABLES)
                    .put(ModBlocks.PALM_LOG.get(), ModBlocks.STRIPPED_PALM_LOG.get())
                    .put(ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_WOOD.get()).build();
        });

        IEventBus bus = MinecraftForge.EVENT_BUS;

        //features
        ModFeatures features = new ModFeatures();
        features.init();
        bus.register(features);

        //network
        /*int index = 0;
        NETWORK.registerMessage(index)*/

    }

    private void clientSetup(FMLClientSetupEvent e) {
        e.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.PAPYRUS.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.PALM_LEAVES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.PALM_SAPLING.get(), RenderType.cutout());
        });

        IEventBus bus = MinecraftForge.EVENT_BUS;

        //key
        ModKeyBindings.register();
        bus.addListener(ModKeyBindings::onKeyPress);

        //screen
        ScreenManager.register(ModContainers.SOUL_INFUSER_CONTAINER.get(), SoulInfuserScreen::new);

    }

}
