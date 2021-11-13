package fr.dynasty.dynastymod.data.client;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {

    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DynastyMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //blocks
        withExistingParent("purified_gold_block", modLoc("block/purified_gold_block"));
        withExistingParent("azurite_block", modLoc("block/azurite_block"));
        withExistingParent("selenite_block", modLoc("block/selenite_block"));
        withExistingParent("solarite_block", modLoc("block/solarite_block"));

        withExistingParent("azurite_ore", modLoc("block/azurite_ore"));
        withExistingParent("selenite_ore", modLoc("block/selenite_ore"));
        withExistingParent("solarite_ore", modLoc("block/solarite_ore"));

        withExistingParent("soul_stone", modLoc("block/soul_stone"));

        withExistingParent("soul_infuser", modLoc("block/soul_infuser"));

        withExistingParent("palm_log", modLoc("block/palm_log"));
        withExistingParent("palm_wood", modLoc("block/palm_wood"));
        withExistingParent("stripped_palm_log", modLoc("block/stripped_palm_log"));
        withExistingParent("stripped_palm_wood", modLoc("block/stripped_palm_wood"));
        withExistingParent("palm_planks", modLoc("block/palm_planks"));
        withExistingParent("palm_leaves", modLoc("block/palm_leaves"));


        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));

        //items
        builder("purified_gold_ingot", itemGenerated);

        builder("azurite", itemGenerated);
        //builder("selenite", itemGenerated);
        //builder("solarite", itemGenerated);

        //builder("selenite_fragment", itemGenerated);
        //builder("solarite_fragment", itemGenerated);

        builder("soul", itemGenerated);

        //builder("date", itemGenerated);

        //builder("papyrus_fiber", itemGenerated);

        builder("raska_fang", itemGenerated);

        //block cross
        //builder("palm_sapling", itemGenerated);
        //builder("papyrus", itemGenerated);

    }

    private void builder(String name , ModelFile itemGenerated) {
        getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
