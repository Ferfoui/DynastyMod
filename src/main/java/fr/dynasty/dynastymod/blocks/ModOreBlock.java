package fr.dynasty.dynastymod.blocks;

import fr.dynasty.dynastymod.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class ModOreBlock extends OreBlock {
    public ModOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected int xpOnDrop(Random random){
        if (this == ModBlocks.AZURITE_ORE.get()) {
            return MathHelper.nextInt(random, 1, 4);
        } else if (this == ModBlocks.SELENITE_ORE.get()) {
            return MathHelper.nextInt(random, 3, 7);
        } else if (this == ModBlocks.SOLARITE_ORE.get()){
            return MathHelper.nextInt(random, 3, 7);
        } else {
            return 0;
        }
    }

    @Override
    public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? this.xpOnDrop(RANDOM) : 0;
    }

}
