package fr.dynasty.dynastymod.world.gen;

import fr.dynasty.dynastymod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreType {

    AZURITE(Lazy.of(ModBlocks.AZURITE_ORE), 3, 10, 40, 10),
    SELENITE(Lazy.of(ModBlocks.SELENITE_ORE), 6, 20, 3),
    SOLARITE(Lazy.of(ModBlocks.SOLARITE_ORE), 6, 20, 3);

    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;
    private final int veinsPerChunk;

    OreType(Lazy<Block> block, int maxVeinSize, int maxHeight, int veinsPerChunk) {
        this(block,maxVeinSize, 0, maxHeight, veinsPerChunk);
    }

    OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight, int veinsPerChunk) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinsPerChunk = veinsPerChunk;
    }


    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static OreType get(Block block) {
        for (OreType ore : values()) {
            if(block == ore.block) {
                return ore;
            }
        }
        return null;
    }
}
