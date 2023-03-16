package com.momo.fd.blocks.blockMisc;

import com.momo.fd.blocks.BlockBase;
import com.momo.fd.blocks.ModBlocks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

public class DeepSlate extends BlockBase {
    public DeepSlate() {
        super("deepslate", Material.ROCK, MapColor.QUARTZ);
        setHardness(3.0F);
        setResistance(6.0F);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 5;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.ROCK_BLOCK);
    }

}
