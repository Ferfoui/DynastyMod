package fr.dynasty.dynastymod.data.client;

import fr.dynasty.dynastymod.DynastyMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider {
    public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DynastyMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("azurite_ore", modLoc("block/azurite_ore"));
        withExistingParent("azurite_block", modLoc("block/azurite_block"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder("azurite", itemGenerated);
    }

    private ItemModelBuilder builder(String name , ModelFile itemGenerated) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
