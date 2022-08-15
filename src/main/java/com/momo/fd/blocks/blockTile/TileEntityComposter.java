package com.momo.fd.blocks.blockTile;

import com.momo.fd.blocks.blockInteractive.Composter;
import com.momo.fd.feature.FeatureComposter;
import com.momo.fd.item.ItemHelper;
import com.momo.fd.network.NetworkHandler;
import com.momo.fd.network.message.MessageComposterAddLevel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityComposter extends TileEntity {

    public final ItemStackHandler inventory = new ItemStackHandler(1);

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        Capability<IItemHandler> itemHandlerCapability = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        return itemHandlerCapability.equals(capability) || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new TileEntityComposter.IItemHandlerWithComposter(facing));
        }
        return super.getCapability(capability, facing);
    }

    public class IItemHandlerWithComposter implements IItemHandler
    {
        private final EnumFacing facing;

        public IItemHandlerWithComposter(EnumFacing facing) {
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
            if (world.getBlockState(pos).getBlock() instanceof Composter) {
                if (facing != EnumFacing.DOWN && !stack.isEmpty() && addItem(stack, false)) {
                    return ItemStack.EMPTY;
                }
            }

            return stack;
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (facing == EnumFacing.DOWN) {
                return inventory.extractItem(slot, amount, simulate);
            }
            return ItemStack.EMPTY;
        }

        @Override
        public int getSlotLimit(int slot) {
            return inventory.getSlotLimit(slot);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return true;
        }
    }

    public IBlockState getBlockState()
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof Composter) {
            return state;
        }
        return null;
    }

    public boolean addItem(ItemStack inputStack, boolean keepInput)
    {
        IBlockState state = world.getBlockState(pos);
        int level = state.getValue(Composter.LEVEL);
        int newLevel = level;

        if (level == 8) {
            // composter is full
            if (!world.isRemote) {
                ItemStack stack = new ItemStack(Items.DYE, 1, 15);

                // try pushing into neighbour
                boolean inserted = false;
                TileEntity n = world.getTileEntity(pos.down());
                if (n instanceof TileEntityHopper) {
                    IItemHandler ni = n.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
                    if (ni != null) {
                        ItemStack copy = stack.copy();
                        if (ItemHandlerHelper.insertItemStacked(ni, copy, false).isEmpty()) {
                            if (ni instanceof TileEntityHopper) {
                                ((TileEntityHopper) ni).setTransferCooldown(7);
                            }
                            inserted = true;
                            this.markDirty();
                        }
                    }
                }
                if (!inserted) {
                    // if couldn't push into neighbour container, pop it out the top of the composter
                    if(!world.isRemote){
                        world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), stack));
                    }
                }
            }
            newLevel = 0;

            if(world.isRemote)
            {
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), ModSoundHandler.BLOCK_COMPOSTER_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

        } else {

            // check item and increase level based on item chance
            String itemName = ItemHelper.getItemStringFromItemStack(inputStack, true);

            if (!FeatureComposter.inputs.containsKey(itemName)) {
                itemName = itemName.substring(0, itemName.indexOf('['));
            }

            // try again after stripping metadata bit
            if (!FeatureComposter.inputs.containsKey(itemName)) return false;

            if (!world.isRemote) {
                if (!keepInput) {
                    inputStack.shrink(1);
                }
                if (world.rand.nextFloat() < FeatureComposter.inputs.get(itemName)) {
                    newLevel++;
                }
            }

            // let clients know the level has increased
            NetworkHandler.channel.sendToAll(new MessageComposterAddLevel(pos, newLevel, level));
        }

        if (newLevel != level) {
            world.setBlockState(pos, state.withProperty(Composter.LEVEL, newLevel), 2);
            world.updateComparatorOutputLevel(pos, null);
        }

        return true;
    }
}
