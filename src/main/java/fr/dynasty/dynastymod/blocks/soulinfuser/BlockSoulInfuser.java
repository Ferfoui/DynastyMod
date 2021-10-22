package fr.dynasty.dynastymod.blocks.soulinfuser;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockSoulInfuser extends ContainerBlock {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public BlockSoulInfuser() {
        super(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.SOUL_SOIL).strength(2.5f, 3f).harvestTool(ToolType.PICKAXE).harvestLevel(1).requiresCorrectToolForDrops()/*.lightLevel((BlockSoulInfuser::getLightValue))*/);
        //this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.FALSE));
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
     *//*
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
    }*/

    // ---------------------

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return newBlockEntity(world);
    }

    @Nullable
    @Override
    public TileEntity newBlockEntity(IBlockReader world) {
        return new TileEntitySoulInfuser();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        this.interactWith(world, pos, player);
        return ActionResultType.CONSUME;
    }

    private void interactWith(World world, BlockPos pos, PlayerEntity player) {
        TileEntity tileEntity = world.getBlockEntity(pos);
        if (tileEntity instanceof TileEntitySoulInfuser && player instanceof ServerPlayerEntity) {
            TileEntitySoulInfuser te = (TileEntitySoulInfuser) tileEntity;
            NetworkHooks.openGui((ServerPlayerEntity) player, te, te::encodeExtraData);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state1, World world, BlockPos pos, BlockState state2, boolean isMoving) {
        if (!state1.is(state2.getBlock())) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof IInventory) {
                InventoryHelper.dropContents(world, pos, (IInventory) tileEntity);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state1, world, pos, state2, isMoving);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

// ---------------------------

    // If you want your container to provide redstone power to a comparator based on its contents, implement these methods
    //  see vanilla for examples
/*
    //hasComparatorInputOverride
    @SuppressWarnings("deprecation")
    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    //getComparatorInputOverride
    @SuppressWarnings("deprecation")
    @Override
    public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.getRedstoneSignalFromBlockEntity(worldIn.getBlockEntity(pos));
    }
*/
    //------------------------------------------------------------
    //  The code below isn't necessary for illustrating the Inventory Furnace concepts, it's just used for rendering.
    //  For more background information see MBE03

    // render using a BakedModel
    // required because the default (super method) is INVISIBLE for BlockContainers.
    @Override
    public BlockRenderType getRenderShape(BlockState iBlockState) {
        return BlockRenderType.MODEL;
    }

}
