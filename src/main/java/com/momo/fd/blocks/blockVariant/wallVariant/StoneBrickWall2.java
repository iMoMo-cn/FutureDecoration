package com.momo.fd.blocks.blockVariant.wallVariant;


import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class StoneBrickWall2 extends WallVariant
{
    public StoneBrickWall2(int maxUsedVariants) {
        super("stonebrick_wall_2", Material.ROCK, 2.0F, 5.0F, SoundType.STONE, maxUsedVariants);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(VARIANT).getMeta() == 0)  return MapColor.CYAN;
        if (state.getValue(VARIANT).getMeta() == 3)  return MapColor.SAND;
        if (state.getValue(VARIANT).getMeta() == 4)  return MapColor.PURPLE;
        return MapColor.DIAMOND;
    }
}