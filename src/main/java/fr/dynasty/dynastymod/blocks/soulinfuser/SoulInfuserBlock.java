package fr.dynasty.dynastymod.blocks.soulinfuser;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SoulInfuserBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public SoulInfuserBlock() {
        super(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.SOUL_SOIL).strength(2.5f, 3f).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops().lightLevel((SoulInfuserBlock::getLightValue)));
    }
    // --- The block changes its appearance depending on how many of the furnace slots have burning fuel in them
    //  In order to do that, we add a blockstate for each state (0 -> 4), each with a corresponding model.  We also change the blockLight emitted.

    final static int MAX_NUMBER_OF_BURNING_SIDES = 4;
    public static final IntegerProperty BURNING_SIDES_COUNT = IntegerProperty.create("burning_sides_count",0, MAX_NUMBER_OF_BURNING_SIDES);

    // change the furnace emitted light ("block light") depending on how many slots are burning
    private static final int ALL_SIDES_LIGHT_VALUE = 15; // light value for four sides burning
    private static final int ONE_SIDE_LIGHT_VALUE = 8;  // light value for a single side burning

    /**
     * Amount of block light emitted by the furnace
     * This function is registered in the Block Properties using func_235838_a;  see BlockInventoryFurnace constructor
     */
    public static int getLightValue(BlockState state) {
        int lightValue = 0;
        Integer burningSidesCount = state.getValue(BURNING_SIDES_COUNT);

        if (burningSidesCount == 0) {
            lightValue = 0;
        } else {
            // linearly interpolate the light value depending on how many slots are burning
            lightValue = ONE_SIDE_LIGHT_VALUE +
                    (ALL_SIDES_LIGHT_VALUE - ONE_SIDE_LIGHT_VALUE) * burningSidesCount / (MAX_NUMBER_OF_BURNING_SIDES - 1);
        }
        lightValue = MathHelper.clamp(lightValue, 0, ALL_SIDES_LIGHT_VALUE);
        return lightValue;
    }

    // ---------------------

    /**
     * Create the Tile Entity for this block.
     * Forge has a default but I've included it anyway for clarity
     * @return
     */

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntitySoulInfuser();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {
        return super.use(blockState, world, pos, player, hand, rayTrace);
    }

    // ---------------------------
    // If you want your container to provide redstone power to a comparator based on its contents, implement these methods
    //  see vanilla for examples
/*
    //hasComparatorInputOverride
    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return false;
    }

    //getComparatorInputOverride
    @Override
    public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
        return 0;
    }
*/

}
