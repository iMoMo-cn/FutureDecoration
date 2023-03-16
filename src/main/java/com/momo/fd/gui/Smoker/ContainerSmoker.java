package com.momo.fd.gui.Smoker;

import com.momo.fd.blocks.blockTile.TileEntitySmoker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import static net.minecraft.tileentity.TileEntityFurnace.isItemFuel;

public class ContainerSmoker extends Container {
    private final World world;
    private final BlockPos pos;
    private int cookTime;
    private int totalCookTime;
    private int smokerBurnTime;
    private int currentItemBurnTime;

    public ContainerSmoker(EntityPlayer player, World world, int x, int y, int z) {
        this.world = world;
        this.pos = new BlockPos(x, y, z);

        TileEntity tileEntity = world.getTileEntity(this.pos);
        if (tileEntity instanceof TileEntitySmoker) {
            ItemStackHandler inventory = ((TileEntitySmoker) tileEntity).inventory;

            this.addSlotToContainer(new SlotItemHandler(inventory, 0, 56, 17));
            this.addSlotToContainer(new SlotItemHandler(inventory, 1, 56, 53)
            {
                @Override
                public boolean isItemValid(ItemStack stack) { return isItemFuel(stack); }
            });
            this.addSlotToContainer(new SlotSmokerOutput(player, inventory, 2, 116, 35));

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

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        TileEntity tileEntity = world.getTileEntity(this.pos);
        if (tileEntity instanceof TileEntitySmoker)
        {

            TileEntitySmoker tileSmoker = (TileEntitySmoker)tileEntity;

            for (IContainerListener listener : this.listeners) {

                if (this.smokerBurnTime != tileSmoker.getField(0)) {
                    listener.sendWindowProperty(this, 0, tileSmoker.getField(0));
                }

                if (this.currentItemBurnTime != tileSmoker.getField(1)) {
                    listener.sendWindowProperty(this, 1, tileSmoker.getField(1));
                }

                if (this.cookTime != tileSmoker.getField(2)) {
                    listener.sendWindowProperty(this, 2, tileSmoker.getField(2));
                }

                if (this.totalCookTime != tileSmoker.getField(3)) {
                    listener.sendWindowProperty(this, 3, tileSmoker.getField(3));
                }
            }

            this.cookTime = tileSmoker.getField(2);
            this.smokerBurnTime = tileSmoker.getField(0);
            this.currentItemBurnTime = tileSmoker.getField(1);
            this.totalCookTime = tileSmoker.getField(3);
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        TileEntity tileEntity = world.getTileEntity(this.pos);
        if (tileEntity instanceof TileEntitySmoker) {

            TileEntitySmoker tileSmoker = (TileEntitySmoker) tileEntity;
            tileSmoker.setField(id, data);
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn)
    {
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

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (!FurnaceRecipes.instance().getSmeltingResult(itemstack1).isEmpty())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
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

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

}
