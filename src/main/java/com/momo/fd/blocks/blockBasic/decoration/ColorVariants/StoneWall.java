package com.momo.fd.blocks.blockBasic.decoration.ColorVariants;


import com.momo.fd.blocks.blockVariant.WallVariant;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class StoneWall extends WallVariant
{
    public StoneWall(int maxUsedVariants) {
        super("stone_wall", Material.ROCK, 1.5F, 3.33F, SoundType.STONE, maxUsedVariants);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(VARIANT).getMeta() == 3)  return MapColor.DIRT;
        if (state.getValue(VARIANT).getMeta() == 2)  return MapColor.QUARTZ;
        if (state.getValue(VARIANT).getMeta() == 4)  return MapColor.BLACK;
        if (state.getValue(VARIANT).getMeta() == 5)  return MapColor.BLACK;
        return MapColor.STONE;
    }
}