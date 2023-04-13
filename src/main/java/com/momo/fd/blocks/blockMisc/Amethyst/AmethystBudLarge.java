package com.momo.fd.blocks.blockMisc.Amethyst;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class AmethystBudLarge extends AmethystBud {
    protected static final AxisAlignedBB LARGE_UP_AABB = new AxisAlignedBB(0.155D, 0.0D, 0.155D, 0.845D, 0.625D, 0.845D);
    protected static final AxisAlignedBB LARGE_DOWN_AABB = new AxisAlignedBB(0.155D, 0.375D, 0.155D, 0.845D, 1.0D, 0.845D);
    protected static final AxisAlignedBB LARGE_NORTH_AABB = new AxisAlignedBB(0.155D, 0.155D, 0.375D, 0.845D, 0.845D, 1.0D);
    protected static final AxisAlignedBB LARGE_SOUTH_AABB = new AxisAlignedBB(0.155D, 0.155D, 0.0D, 0.845D, 0.845D, 0.625D);
    protected static final AxisAlignedBB LARGE_WEST_AABB = new AxisAlignedBB(0.375D, 0.155D, 0.155D, 1.0D, 0.845D, 0.845D);
    protected static final AxisAlignedBB LARGE_EAST_AABB = new AxisAlignedBB(0.0D, 0.155D, 0.155D, 0.625D, 0.845D, 0.845D);

    public AmethystBudLarge(String name, MapColor mapColor) {
        super(name, mapColor);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        AxisAlignedBB AABB = LARGE_UP_AABB;
        switch(state.getValue(FACING).getIndex())
        {
            case 0: AABB = LARGE_DOWN_AABB; break;
            case 1: AABB = LARGE_UP_AABB; break;
            case 2: AABB = LARGE_NORTH_AABB; break;
            case 3: AABB = LARGE_SOUTH_AABB; break;
            case 4: AABB = LARGE_WEST_AABB; break;
            case 5: AABB = LARGE_EAST_AABB; break;
        }
        return AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        AxisAlignedBB AABB = LARGE_UP_AABB;
        switch(blockState.getValue(FACING).getIndex())
        {
            case 0: AABB = LARGE_DOWN_AABB; break;
            case 1: AABB = LARGE_UP_AABB; break;
            case 2: AABB = LARGE_NORTH_AABB; break;
            case 3: AABB = LARGE_SOUTH_AABB; break;
            case 4: AABB = LARGE_WEST_AABB; break;
            case 5: AABB = LARGE_EAST_AABB; break;
        }
        return AABB;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { return 4; }
}
