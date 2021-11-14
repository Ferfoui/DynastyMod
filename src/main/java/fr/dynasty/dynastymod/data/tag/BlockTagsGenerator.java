package fr.dynasty.dynastymod.data.tag;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.utils.ModTags;
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
        tag(ModTags.Blocks.CELESTIAL_BLOCKS).add(ModBlocks.SELENITE_BLOCK.get(), ModBlocks.SOLARITE_BLOCK.get());
        tag(BlockTags.PLANKS).add(ModBlocks.PALM_PLANKS.get());
        tag(ModTags.Blocks.PALM_LOGS).add(ModBlocks.PALM_LOG.get(), ModBlocks.PALM_WOOD.get(), ModBlocks.STRIPPED_PALM_LOG.get(), ModBlocks.STRIPPED_PALM_WOOD.get());

        tag(BlockTags.LOGS_THAT_BURN).addTag(ModTags.Blocks.PALM_LOGS);
        tag(BlockTags.LOGS).addTag(BlockTags.LOGS_THAT_BURN);
        tag(BlockTags.SAPLINGS).add(ModBlocks.PALM_SAPLING.get());
        tag(BlockTags.LEAVES).add(ModBlocks.PALM_LEAVES.get());
        tag(BlockTags.STANDING_SIGNS).add(ModBlocks.PALM_SIGN.get());
        tag(BlockTags.WALL_SIGNS).add(ModBlocks.PALM_WALL_SIGN.get());
        tag(BlockTags.SIGNS).addTag(BlockTags.STANDING_SIGNS).addTag(BlockTags.WALL_SIGNS);
    }

}
