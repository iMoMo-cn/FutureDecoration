package com.momo.fd.blocks.blockBasic;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BurnableBlock extends Block implements IHasModel {
    public BurnableBlock(String name, int burnTime, Material material, MapColor mapColor){
        super(material, mapColor);
        setUnlocalizedName(name);
        setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this){
            @Override
            public int getItemBurnTime(ItemStack itemStack)
            {
                return burnTime;
            }
        }.setRegistryName(this.getRegistryName()));
    }

    public BurnableBlock(String name, int burnTime, Material material){
        this(name, burnTime, material, material.getMaterialMapColor());
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
