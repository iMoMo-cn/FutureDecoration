package com.momo.fd.gui.Barrel;

import com.momo.fd.blocks.blockTile.TileEntityBarrel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBarrel extends Container {

    private final World world;
    private final BlockPos pos;

    public ContainerBarrel(EntityPlayer player, World world, int x, int y, int z) {
        this.world = world;
        this.pos = new BlockPos(x, y, z);

        TileEntity barrel = world.getTileEntity(this.pos);

        if (barrel instanceof TileEntityBarrel) {
            ItemStackHandler inventory = ((TileEntityBarrel) barrel).inventory;

            if (inventory == null) return;

            // 27 slots for the barrel's inventory
            int x1 = 8;
            int y1 = 18;
            int slotIndex = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    addSlotToContainer(new SlotItemHandler(inventory, slotIndex, x1, y1)
                    {
                        @Override
                        public void onSlotChanged()
                        {
                            barrel.markDirty();
                        }
                    });
                    slotIndex++;
                    x1 += 18;
                }
                x1 = 8;
                y1 += 18;
            }


            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 9; ++j) {
                    this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                }
            }

            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(player.inventory, k, 8 + k * 18, 142));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.world.equals(this.world) && playerIn.getDistanceSq(this.pos) <= 64.0;
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 27)
            {
                if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 27, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        playerIn.world.playSound(null,  (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, ModSoundHandler.BLOCK_BARREL_CLOSE, SoundCategory.BLOCKS, 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    }
}
