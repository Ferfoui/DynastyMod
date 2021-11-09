package fr.dynasty.dynastymod.blocks.soulinfuser;

import fr.dynasty.dynastymod.init.ModContainers;
import fr.dynasty.dynastymod.utils.ModTags;
import fr.dynasty.dynastymod.utils.tag.ModItemTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SoulInfuserContainer extends Container {
    private final IInventory inventory;
    private final IIntArray fields;

    public SoulInfuserContainer(int id, PlayerInventory playerInventory, PacketBuffer buffer) {
        this(id, playerInventory, new SoulInfuserTileEntity(), new IntArray(buffer.readByte()));
    }

    public SoulInfuserContainer(int id, PlayerInventory playerInventory, IInventory inventory, IIntArray fields) {
        super(ModContainers.SOUL_INFUSER_CONTAINER.get(), id);
        this.inventory = inventory;
        this.fields = fields;

        //Slots
        this.addSlot(new Slot(this.inventory, 0, 56, 17));
        this.addSlot(new Slot(this.inventory, 1, 56, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return isInfuser(stack);
            }
        });
        this.addSlot(new Slot(this.inventory, 2, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        //Player inventory
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                int index = x + y * 9 + 9;
                int posX = 8 + x * 18;
                int posY = 84 + y * 18;
                this.addSlot(new Slot(playerInventory, index, posX, posY));
            }
        }

        //Player hotbar
        for (int x = 0; x < 9; ++x) {
            // x = index
            int posX = 8 + x * 18;
            int posY = 142;
            this.addSlot(new Slot(playerInventory, x, posX, posY));
        }

        this.addDataSlots(fields);
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();

            final int inventorySize = 3;
            final int playerInventoryEnd = inventorySize + 27;
            final int playerHotbarEnd = playerInventoryEnd + 9;

            if (index == 2) {
                if (!this.moveItemStackTo(stack, inventorySize, playerHotbarEnd, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(stack, itemstack);
            } else if (index != 1 && index != 0) {
                if (this.isInfuser(stack)) {
                    if (!this.moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(stack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, inventorySize, playerHotbarEnd, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }

    private boolean isInfuser(ItemStack stack) {
        return stack.getItem().is(ModTags.Items.INFUSER_ITEMS);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.inventory.stillValid(player);
    }

    @OnlyIn(Dist.CLIENT)
    public int getProgressArrowScale() {
        int progress = this.fields.get(0);
        int infusingTime = this.fields.get(1);
        if (isLit()) {
            return progress * 24 / infusingTime;
        }
        return 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getSoulBurning() {
        int infusingTime = fields.get(1);

        return infusingTime * 13 / infusingTime;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isLit() {
        int progress = this.fields.get(0);
        return progress > 0;
    }

}
