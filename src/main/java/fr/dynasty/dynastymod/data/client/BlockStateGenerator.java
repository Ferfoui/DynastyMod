package fr.dynasty.dynastymod.data.client;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStateGenerator extends BlockStateProvider {
    
    public BlockStateGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, DynastyMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.PURIFIED_GOLD_BLOCK.get());

        simpleBlock(ModBlocks.AZURITE_BLOCK.get());
        //simpleBlock(ModBlocks.SELENITE_BLOCK.get());
        //simpleBlock(ModBlocks.SOLARITE_BLOCK.get());

        simpleBlock(ModBlocks.AZURITE_ORE.get());
        simpleBlock(ModBlocks.SELENITE_ORE.get());
        simpleBlock(ModBlocks.SOLARITE_ORE.get());

        logBlock((RotatedPillarBlock) ModBlocks.PALM_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PALM_LOG.get());
        woodBlock((RotatedPillarBlock) ModBlocks.PALM_WOOD.get(), blockTexture(ModBlocks.PALM_LOG.get()));
        woodBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PALM_WOOD.get(), blockTexture(ModBlocks.STRIPPED_PALM_LOG.get()));

        simpleBlock(ModBlocks.PALM_PLANKS.get());
        stairsBlock((StairsBlock) ModBlocks.PALM_STAIRS.get(), blockTexture(ModBlocks.PALM_PLANKS.get()));
        slabBlock((SlabBlock) ModBlocks.PALM_SLAB.get(), blockTexture(ModBlocks.PALM_PLANKS.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));
        fenceBlock((FenceBlock) ModBlocks.PALM_FENCE.get(), blockTexture(ModBlocks.PALM_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.PALM_FENCE_GATE.get(), blockTexture(ModBlocks.PALM_PLANKS.get()));

    }

    public void woodBlock(RotatedPillarBlock block, ResourceLocation logResourceLocation) {
        BlockModelBuilder model = models().cubeColumn(name(block), logResourceLocation, logResourceLocation);
        axisBlock(block, model, model);
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

}
