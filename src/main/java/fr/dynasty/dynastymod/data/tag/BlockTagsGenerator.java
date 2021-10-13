package fr.dynasty.dynastymod.data.tag;

import fr.dynasty.dynastymod.init.ModBlocks;
import fr.dynasty.dynastymod.utils.tag.ModBlockTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class BlockTagsGenerator extends BlockTagsProvider {


    public BlockTagsGenerator(DataGenerator dataGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ModBlockTags.SELENITE_ORES).add(ModBlocks.SELENITE_ORE.get());
        this.tag(ModBlockTags.CELESTIAL_BLOCKS).add(ModBlocks.SELENITE_BLOCK.get(),ModBlocks.SOLARITE_BLOCK.get());

    }

}
