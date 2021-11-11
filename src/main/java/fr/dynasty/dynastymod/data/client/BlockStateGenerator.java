package fr.dynasty.dynastymod.data.client;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModBlocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGenerator extends BlockStateProvider {
    
    public BlockStateGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DynastyMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.PURIFIED_GOLD_BLOCK.get());

        //simpleBlock(ModBlocks.AZURITE_BLOCK.get());
        //simpleBlock(ModBlocks.SELENITE_BLOCK.get());
        //simpleBlock(ModBlocks.SOLARITE_BLOCK.get());

        simpleBlock(ModBlocks.AZURITE_ORE.get());
        simpleBlock(ModBlocks.SELENITE_ORE.get());
        simpleBlock(ModBlocks.SOLARITE_ORE.get());

        //logBlock((RotatedPillarBlock) ModBlocks.PALM_LOG.get());
        //logBlock((RotatedPillarBlock) ModBlocks.PALM_WOOD.get());
        //logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PALM_LOG.get());
        //logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PALM_WOOD.get());

        simpleBlock(ModBlocks.PALM_PLANKS.get());

    }
}
