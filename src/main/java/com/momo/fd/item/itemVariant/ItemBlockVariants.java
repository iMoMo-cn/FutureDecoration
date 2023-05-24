package com.momo.fd.item.itemVariant;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.blockVariant.baseVariant.IMetaName;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockVariants extends ItemBlock implements IHasModel {
    public final int maxVariants;

    public ItemBlockVariants(Block block, int maxVariants) {
        super(block);
        setHasSubtypes(true);
        setMaxDamage(0);
        this.maxVariants = maxVariants;
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "_" + ((IMetaName)this.block).getSpecialName(stack);
    }

    @Override
    public void registerModels()
    {
        for (int i = 0; i < maxVariants; i++)
        {
            MoMoFramework.proxy.registerItemRenderer(this, i, "inventory");
        }
    }
}
