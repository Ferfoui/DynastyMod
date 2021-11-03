package fr.dynasty.dynastymod.data.tag;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.utils.tag.ModBlockTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BlockTagsGenerator extends BlockTagsProvider {

    public BlockTagsGenerator(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, DynastyMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(ModBlockTags.CELESTIAL_BLOCKS).add(ModBlocks.SELENITE_BLOCK.get(), ModBlocks.SOLARITE_BLOCK.get());
        tag(BlockTags.PLANKS).add(ModBlocks.PALM_PLANKS.get());
        tag(ModBlockTags.PALM_LOGS).add(ModBlocks.PALM_LOG.get(), ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_LOG.get(), ModBlocks.STRIPPED_PALM_WOOD.get());

        tag(BlockTags.LOGS_THAT_BURN).addTag(ModBlockTags.PALM_LOGS);
        tag(BlockTags.LOGS).addTag(BlockTags.LOGS_THAT_BURN);
        tag(BlockTags.SAPLINGS).add(ModBlocks.PALM_SAPLING.get());
        tag(BlockTags.LEAVES).add(ModBlocks.PALM_LEAVES.get());
    }

}
