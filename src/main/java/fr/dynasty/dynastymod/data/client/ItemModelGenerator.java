package fr.dynasty.dynastymod.data.client;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {

    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DynastyMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //blocks
        withExistingParent(ModBlocks.PURIFIED_GOLD_BLOCK.get());
        withExistingParent(ModBlocks.AZURITE_BLOCK.get());
        withExistingParent(ModBlocks.SELENITE_BLOCK.get());
        withExistingParent(ModBlocks.SOLARITE_BLOCK.get());

        withExistingParent(ModBlocks.AZURITE_ORE.get());
        withExistingParent(ModBlocks.SELENITE_BLOCK.get());
        withExistingParent(ModBlocks.SOLARITE_ORE.get());

        withExistingParent(ModBlocks.SOUL_STONE.get());

        withExistingParent(ModBlocks.SOUL_INFUSER.get());

        withExistingParent(ModBlocks.PALM_LOG.get());
        withExistingParent(ModBlocks.PALM_WOOD.get());
        withExistingParent(ModBlocks.STRIPPED_PALM_LOG.get());
        withExistingParent(ModBlocks.STRIPPED_PALM_WOOD.get());
        withExistingParent(ModBlocks.PALM_LEAVES.get());
        withExistingParent(ModBlocks.PALM_PLANKS.get());
        withExistingParent(ModBlocks.PALM_STAIRS.get());
        withExistingParent(ModBlocks.PALM_SLAB.get());
        withExistingParent("palm_fence", DynastyMod.rl("block/palm_fence_inventory"));
        withExistingParent(ModBlocks.PALM_FENCE_GATE.get());


        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));

        //items
        builder(ModItems.PURIFIED_GOLD_INGOT.get(), itemGenerated);

        builder(ModItems.AZURITE.get(), itemGenerated);
        //builder("selenite", itemGenerated);
        //builder("solarite", itemGenerated);

        //builder("selenite_fragment", itemGenerated);
        //builder("solarite_fragment", itemGenerated);

        builder(ModItems.SOUL.get(), itemGenerated);

        //builder("date", itemGenerated);

        //builder("papyrus_fiber", itemGenerated);

        builder(ModItems.RICK_ASTLEY_MUSIC_DISC.get(), itemGenerated);

        builder(ModItems.RASKA_FANG.get(), itemGenerated);

        //block cross
        //builder("palm_sapling", itemGenerated);
        //builder("papyrus", itemGenerated);

    }

    private void builder(Item item, ModelFile model) {
        getBuilder(item.getRegistryName().getPath()).parent(model).texture("layer0", "item/" + item);
    }

    private void builder(String name, ModelFile model) {
        getBuilder(name).parent(model).texture("layer0", "item/" + name);
    }

    private ResourceLocation blockTexture(Block block) {
        ResourceLocation name = block.getRegistryName();
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath());
    }

    private void withExistingParent(Block block) {
        withExistingParent(block.getRegistryName().getPath(), blockTexture(block));
    }

}
