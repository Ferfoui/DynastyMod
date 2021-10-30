package fr.dynasty.dynastymod.blocks.soulinfuser;

import fr.dynasty.dynastymod.DynastyMod;
import fr.dynasty.dynastymod.init.ModRecipes;
import fr.dynasty.dynastymod.init.ModTileEntities;
import fr.dynasty.dynastymod.utils.crafting.recipe.InfusingRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nullable;

public class TileEntitySoulInfuser extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {

    private final int DEFAULT_INFUSING_TIME = 200;
    
    private NonNullList<ItemStack> items;

    private final LazyOptional<? extends IItemHandler>[] handlers;

    private int progress;
    private int infusingTime;

    private final IIntArray fields = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return progress;
                case 1:
                    return infusingTime;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    progress = value;
                    break;
                case 1:
                    infusingTime = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public TileEntitySoulInfuser() {
        super(ModTileEntities.SOUL_INFUSER_TILE_ENTITY.get());
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
        this.items = NonNullList.withSize(3, ItemStack.EMPTY);
    }

    void encodeExtraData(PacketBuffer buffer) {
        buffer.writeByte(this.fields.getCount());
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide) return;

        InfusingRecipe recipe = getRecipe();
        if (recipe != null) {
            doWork(recipe);
        } else {
            stopWork();
        }
    }

    @Nullable
    public InfusingRecipe getRecipe() {
        if (this.level == null || getItem(0).isEmpty() || getItem(1).isEmpty()) {
            return null;
        }
        return this.level.getRecipeManager().getRecipeFor(ModRecipes.Types.INFUSING, this, this.level).orElse(null);
    }

    private ItemStack getWorkOutput(@Nullable InfusingRecipe recipe) {
        if (recipe != null) {
            return recipe.assemble(this);
        }
        return ItemStack.EMPTY;
    }

    private void doWork(InfusingRecipe recipe) {
        assert this.level != null;

        ItemStack current = getItem(2);
        ItemStack output = getWorkOutput(recipe);

        if (!current.isEmpty()) {
            int newCount = current.getCount() + output.getCount();

            if (!current.sameItem(output) || newCount > output.getMaxStackSize()) {
                stopWork();
                return;
            }
        }

        if (recipe.getInfusingTime() <= 0) {
            this.infusingTime = DEFAULT_INFUSING_TIME;
        } else {
            this.infusingTime = recipe.getInfusingTime();
        }

        if (progress < infusingTime) {
            ++progress;
        }

        if (progress >= infusingTime) {
            finishWork(recipe, current, output);
        }

        sendUpdate(this.getBlockState().setValue(BlockSoulInfuser.LIT, true));
    }

    private void finishWork(InfusingRecipe recipe, ItemStack current, ItemStack output) {
        if (recipe != getRecipe()) {
            this.progress = 0;
            return;
        }

        if (!current.isEmpty()) {
            current.grow(output.getCount());
        } else {
            setItem(2, output);
        }

        this.progress = 0;
        this.infusingTime = DEFAULT_INFUSING_TIME;
        this.removeItem(0, 1);
        this.removeItem(1, 1);
    }

    private void stopWork() {
        this.progress = 0;
        this.infusingTime = DEFAULT_INFUSING_TIME;
        sendUpdate(this.getBlockState().setValue(BlockSoulInfuser.LIT, false));
    }

    private void sendUpdate(BlockState newState) {
        if (level == null) return;
        BlockState oldState = level.getBlockState(worldPosition);
        if (oldState != newState) {
            level.setBlock(worldPosition, newState, 3);
            level.sendBlockUpdated(worldPosition, oldState, newState, 3);
        }
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[]{0, 1, 2};
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return index == 2;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + DynastyMod.MODID + ".soul_infuser");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new ContainerSoulInfuser(id, playerInventory, this, this.fields);
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ItemStackHelper.removeItem(this.items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.items.set(index, stack);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.level != null
                && this.level.getBlockEntity(this.worldPosition) == this
                && player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5) <= 64;
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public void load(BlockState state, CompoundNBT tags) {
        super.load(state, tags);
        this.items = NonNullList.withSize(3, ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(tags, this.items);

        this.progress = tags.getInt("Progress");
        this.infusingTime = tags.getInt("InfusingTime");
    }

    @Override
    public CompoundNBT save(CompoundNBT tags) {
        super.save(tags);
        ItemStackHelper.saveAllItems(tags, this.items);
        tags.putInt("Progress", this.progress);
        tags.putInt("InfusingTime", this.infusingTime);
        return tags;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tags = this.getUpdateTag();
        ItemStackHelper.saveAllItems(tags, this.items);
        return new SUpdateTileEntityPacket(this.worldPosition, 1, tags);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tags = super.getUpdateTag();
        tags.putInt("Progress", this.progress);
        return tags;
    }

    @Nullable
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (!this.remove && side != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.UP) {
                return this.handlers[0].cast();
            } else if (side == Direction.DOWN) {
                return this.handlers[1].cast();
            } else {
                return this.handlers[2].cast();
            }
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();

        for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
            handler.invalidate();
        }
    }
}
