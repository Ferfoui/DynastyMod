package fr.dynasty.dynastymod.blocks.soulinfuser;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

public class SoulInfuser extends Block {

    public SoulInfuser() {
        super(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.SOUL_SOIL).strength(2.5f, 3f).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntitySoulInfuser();
    }
}
