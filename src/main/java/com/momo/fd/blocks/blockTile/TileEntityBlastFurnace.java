package com.momo.fd.blocks.blockTile;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockInteractive.BlastFurnace;
import com.momo.fd.item.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime;
import static net.minecraft.tileentity.TileEntityFurnace.isItemFuel;

public class TileEntityBlastFurnace extends TileEntity implements ITickable {

    // 0 -> up -> upIn
    // 1 -> back -> backIn
    // 2 -> down -> downOut
    public final ItemStackHandler inventory = new ItemStackHandler(3);

    /** The number of ticks that the smoker will keep burning */
    private int smokerBurnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the smoker burning for */
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime = 100;
    private String smokerCustomName;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.smokerBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        compound.setTag("Inventory", this.inventory.serializeNBT());
        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.smokerCustomName);
        }
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.smokerBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
        this.currentItemBurnTime = getItemBurnTime(inventory.getStackInSlot(1));
        if (compound.hasKey("CustomName", 8))
        {
            this.smokerCustomName = compound.getString("CustomName");
        }
    }

    /**
     * Get the name of this object. For players this returns their username
     */
    public String getName()
    {
        return this.hasCustomName() ? this.smokerCustomName : "tile.blast_furnace.name";
    }

    public void setCustomInventoryName(String name)
    {
        this.smokerCustomName = name;
    }

    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.smokerCustomName != null && !this.smokerCustomName.isEmpty();
    }

    public ITextComponent getDisplayName()
    {
        return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.smokerBurnTime > 0;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
    {
        if(oldState.getBlock() == ModBlocks.BLAST_FURNACE && newSate.getBlock() == ModBlocks.BLAST_FURNACE){
            return false;
        }
        else
            return oldState != newSate;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(TileEntityBlastFurnace tileBlastFurnace)
    {
        return tileBlastFurnace.getField(0) > 0;
    }

    public void update()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.smokerBurnTime;
            --this.smokerBurnTime;
        }

        if (!this.world.isRemote)
        {
            ItemStack itemstack = this.inventory.getStackInSlot(1);

            if (this.isBurning() || !itemstack.isEmpty() && !(this.inventory.getStackInSlot(0)).isEmpty())
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.smokerBurnTime = getItemBurnTime(itemstack);
                    this.currentItemBurnTime = this.smokerBurnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);

                            if (itemstack.isEmpty())
                            {
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.inventory.setStackInSlot(1, item1);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = getCookTime(this.inventory.getStackInSlot(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (flag != this.isBurning())
            {
                flag1 = true;
                BlastFurnace.setState(this.isBurning(), this.world, this.pos);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    public int getCookTime(ItemStack stack)
    {
        return 100;
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.inventory.getStackInSlot(0).isEmpty() || !canSmoke())
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.inventory.getStackInSlot(0));

            if (itemstack.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = this.inventory.getStackInSlot(2);

                if (itemstack1.isEmpty())
                {
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack))
                {
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                    return true;
                }
                else
                {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        }
    }

    private boolean canSmoke()
    {
        Item item = this.inventory.getStackInSlot(0).getItem();

        if(item == Item.getItemFromBlock(Blocks.IRON_ORE) || item == Item.getItemFromBlock(Blocks.GOLD_ORE) || item == Item.getItemFromBlock(Blocks.COAL_ORE)
                || item == Item.getItemFromBlock(Blocks.DIAMOND_ORE) || item == Item.getItemFromBlock(Blocks.EMERALD_ORE) || item == Item.getItemFromBlock(Blocks.LAPIS_ORE)
                || item == Item.getItemFromBlock(Blocks.REDSTONE_ORE) || item == Item.getItemFromBlock(Blocks.QUARTZ_ORE) || item == Item.getItemFromBlock(ModBlocks.DEEPSLATE_REDSTONE_ORE)
                || item == ModItems.RAW_ORE || item == Item.getItemFromBlock(ModBlocks.COPPER_ORE) || item == Item.getItemFromBlock(ModBlocks.ORE)
                || item == Item.getItemFromBlock(ModBlocks.NETHER_ORE) || item == Item.getItemFromBlock(ModBlocks.RAW_ORE))
        {
            return true;
        }
        return false;
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = this.inventory.getStackInSlot(0);
            ItemStack itemstack1 = FurnaceRecipes.instance().getSmeltingResult(itemstack);
            ItemStack itemstack2 = this.inventory.getStackInSlot(2);

            if (itemstack2.isEmpty())
            {
                this.inventory.setStackInSlot(2, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.grow(itemstack1.getCount());
            }

            itemstack.shrink(1);
        }
    }

    public int getField(int id)
    {
        switch (id)
        {
            case 0:
                return this.smokerBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.smokerBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        return itemHandlerCapability.equals(capability) || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new IItemHandlerWithSmoker(facing));
        }
        return super.getCapability(capability, facing);
    }

    public class IItemHandlerWithSmoker implements IItemHandler {

        private final EnumFacing facing;

        public IItemHandlerWithSmoker(EnumFacing facing) {
            this.facing = facing;
        }

        @Override
        public int getSlots() {
            return inventory.getSlots();
        }

        @Nonnull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return inventory.getStackInSlot(slot);
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (slot == 0 && facing == EnumFacing.UP)
            {
                return inventory.insertItem(0, stack, simulate);
            }
            else if (slot == 1 && isItemFuel(stack))
            {
                if (facing != EnumFacing.UP && facing != EnumFacing.DOWN)
                    return inventory.insertItem(1, stack, simulate);
            }
            return stack;
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (facing == EnumFacing.DOWN)
            {
                return inventory.extractItem(2, amount, simulate);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public int getSlotLimit(int slot) {
            return inventory.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack)
        {
            if(slot == 0)
            {
                return true;
            }
            else if(slot == 1)
            {
                return isItemFuel(stack);
            }
            return false;
        }
    }

}
