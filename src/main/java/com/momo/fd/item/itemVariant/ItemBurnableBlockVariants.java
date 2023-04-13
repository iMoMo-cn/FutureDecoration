package com.momo.fd.item.itemVariant;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBurnableBlockVariants extends ItemBlockVariants{
    public final int burnTime;

    public ItemBurnableBlockVariants(Block block, int maxVariants, int burnTime) {
        super(block, maxVariants);
        this.burnTime = burnTime;
    }

    @Override
    public int getItemBurnTime(ItemStack itemStack)
    {
        return burnTime;
    }
}
