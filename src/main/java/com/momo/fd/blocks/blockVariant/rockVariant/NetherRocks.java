package com.momo.fd.blocks.blockVariant.rockVariant;

import com.momo.fd.blocks.blockVariant.baseVariant.BlockVariantBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class NetherRocks extends BlockVariantBase {
    public NetherRocks(int maxUsedVariants) {
        super("nether_block", Material.ROCK, MapColor.NETHERRACK, maxUsedVariants);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(2.0F);
        setResistance(10.0F);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(VARIANT).getMeta() == 6)  return MapColor.NETHERRACK;
        if (state.getValue(VARIANT).getMeta() == 7)  return MapColor.NETHERRACK;
        if (state.getValue(VARIANT).getMeta() == 8)  return MapColor.NETHERRACK;
        if (state.getValue(VARIANT).getMeta() == 9)  return MapColor.NETHERRACK;
        if (state.getValue(VARIANT).getMeta() == 10)  return MapColor.QUARTZ;
        if (state.getValue(VARIANT).getMeta() == 11)  return MapColor.QUARTZ;
        return MapColor.BLACK_STAINED_HARDENED_CLAY;
    }
}
