package com.momo.fd.blocks.blockVariant.rockVariant;

import com.momo.fd.blocks.blockVariant.baseVariant.BlockVariantBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Rocks extends BlockVariantBase {
    public Rocks(int maxUsedVariants) {
        super("rock_block", Material.ROCK, MapColor.STONE, maxUsedVariants);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(2.0F);
        setResistance(10.0F);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(VARIANT).getMeta() == 4)  return MapColor.QUARTZ;
        if (state.getValue(VARIANT).getMeta() == 5)  return MapColor.DIRT;
        if (state.getValue(VARIANT).getMeta() == 6)  return MapColor.QUARTZ;
        if (state.getValue(VARIANT).getMeta() == 7)  return MapColor.GRAY;
        if (state.getValue(VARIANT).getMeta() == 8)  return MapColor.GRAY;
        if (state.getValue(VARIANT).getMeta() == 9)  return MapColor.GRAY;
        return MapColor.STONE;
    }
}
