package com.momo.fd.blocks.blockVariant;

import com.momo.fd.blocks.blockVariant.baseVariant.BlockVariantBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRawOre extends BlockVariantBase {
    public BlockRawOre() {
        super("raw_ore_block", Material.IRON, MapColor.IRON, 3);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(5.0F);
        setResistance(25.0F);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(VARIANT).getMeta() == 0)  return MapColor.IRON;
        if (state.getValue(VARIANT).getMeta() == 1)  return MapColor.GOLD;
        return MapColor.ORANGE_STAINED_HARDENED_CLAY;
    }


}
