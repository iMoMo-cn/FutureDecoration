package com.momo.fd.blocks.blockBasic.decoration.ColorVariants;


import com.momo.fd.blocks.blockVariant.WallVariant;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class NetherWall extends WallVariant
{
    public NetherWall(int maxUsedVariants) {
        super("nether_wall", Material.ROCK, 2.0F, 5.0F, SoundType.STONE, maxUsedVariants);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(VARIANT).getMeta() == 3)  return MapColor.NETHERRACK;
        if (state.getValue(VARIANT).getMeta() == 4)  return MapColor.NETHERRACK;
        if (state.getValue(VARIANT).getMeta() == 5)  return MapColor.QUARTZ;
        if (state.getValue(VARIANT).getMeta() == 6)  return MapColor.QUARTZ;
        if (state.getValue(VARIANT).getMeta() == 7)  return MapColor.QUARTZ;
        return MapColor.BLACK_STAINED_HARDENED_CLAY;
    }
}