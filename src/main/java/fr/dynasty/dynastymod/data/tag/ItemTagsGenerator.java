package fr.dynasty.dynastymod.data.tag;

import fr.dynasty.dynastymod.init.ModItems;
import fr.dynasty.dynastymod.utils.tag.ModBlockTags;
import fr.dynasty.dynastymod.utils.tag.ModItemTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ItemTagsGenerator extends ItemTagsProvider {

    public ItemTagsGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.copy(ModBlockTags.CELESTIAL_BLOCKS, ModItemTags.CELESTIAL_BLOCKS);
        this.tag(ModItemTags.INFUSING_ITEMS).add(ModItems.SOUL.get());
    }
}

