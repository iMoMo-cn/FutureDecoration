package com.momo.fd.blocks.blockMisc.crystal.Amethyst;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class AmethystBudMedium extends AmethystBud {
    protected static final AxisAlignedBB MEDIUM_UP_AABB = new AxisAlignedBB(0.178D, 0.0D, 0.178D, 0.822D, 0.4375D, 0.822D);
    protected static final AxisAlignedBB MEDIUM_DOWN_AABB = new AxisAlignedBB(0.178D, 0.5625D, 0.178D, 0.822D, 1.0D, 0.822D);
    protected static final AxisAlignedBB MEDIUM_NORTH_AABB = new AxisAlignedBB(0.178D, 0.178D, 0.5625D, 0.822D, 0.625D, 1.0D);
    protected static final AxisAlignedBB MEDIUM_SOUTH_AABB = new AxisAlignedBB(0.178D, 0.178D, 0.0D, 0.822D, 0.822D, 0.4375D);
    protected static final AxisAlignedBB MEDIUM_WEST_AABB = new AxisAlignedBB(0.5625D, 0.178D, 0.178D, 1.0D, 0.822D, 0.822D);
    protected static final AxisAlignedBB MEDIUM_EAST_AABB = new AxisAlignedBB(0.0D, 0.178D, 0.178D, 0.4375D, 0.822D, 0.822D);

    public AmethystBudMedium(String name, MapColor mapColor) {
        super(name, mapColor);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        AxisAlignedBB AABB = MEDIUM_UP_AABB;
        switch(state.getValue(FACING).getIndex())
        {
            case 0: AABB = MEDIUM_DOWN_AABB; break;
            case 1: AABB = MEDIUM_UP_AABB; break;
            case 2: AABB = MEDIUM_NORTH_AABB; break;
            case 3: AABB = MEDIUM_SOUTH_AABB; break;
            case 4: AABB = MEDIUM_WEST_AABB; break;
            case 5: AABB = MEDIUM_EAST_AABB; break;
        }
        return AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        AxisAlignedBB AABB = MEDIUM_UP_AABB;
        switch(blockState.getValue(FACING).getIndex())
        {
            case 0: AABB = MEDIUM_DOWN_AABB; break;
            case 1: AABB = MEDIUM_UP_AABB; break;
            case 2: AABB = MEDIUM_NORTH_AABB; break;
            case 3: AABB = MEDIUM_SOUTH_AABB; break;
            case 4: AABB = MEDIUM_WEST_AABB; break;
            case 5: AABB = MEDIUM_EAST_AABB; break;
        }
        return AABB;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { return 2; }

}
