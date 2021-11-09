package fr.dynasty.dynastymod.data.tag;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModItems;
import fr.dynasty.dynastymod.utils.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ItemTagsGenerator extends ItemTagsProvider {

    public ItemTagsGenerator(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, DynastyMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ModTags.Blocks.CELESTIAL_BLOCKS, ModTags.Items.CELESTIAL_BLOCKS);
        tag(ModTags.Items.INFUSER_ITEMS).add(ModItems.SOUL.get());
        copy(ModTags.Blocks.PALM_LOGS, ModTags.Items.PALM_LOGS);

        copy(BlockTags.PLANKS, ItemTags.PLANKS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        copy(BlockTags.LOGS, ItemTags.LOGS);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
    }
}

