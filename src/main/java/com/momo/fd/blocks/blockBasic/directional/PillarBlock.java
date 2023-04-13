package com.momo.fd.blocks.blockBasic.directional;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class PillarBlock extends BlockRotatedPillar implements IHasModel {

    public PillarBlock(String name, Material material, MapColor mapColor)
    {
        super(material, mapColor);
        setUnlocalizedName(name);
        setRegistryName(name);

        setHardness(2.0F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 0);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}


