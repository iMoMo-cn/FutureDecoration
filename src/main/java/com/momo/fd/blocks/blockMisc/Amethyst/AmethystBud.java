package com.momo.fd.blocks.blockMisc.Amethyst;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;
import java.util.Random;

public class AmethystBud extends AmethystCluster {

    protected static final AxisAlignedBB UP_AABB = new AxisAlignedBB(0.178D, 0.0D, 0.178D, 0.822D, 0.3125D, 0.822D);
    protected static final AxisAlignedBB DOWN_AABB = new AxisAlignedBB(0.178D, 0.6875D, 0.178D, 0.822D, 1.0D, 0.822D);
    protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.178D, 0.178D, 0.6875D, 0.822D, 0.822D, 1.0D);
    protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.178D, 0.178D, 0.0D, 0.822D, 0.822D, 0.3125D);
    protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.6875D, 0.178D, 0.178D, 1.0D, 0.822D, 0.822D);
    protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.178D, 0.178D, 0.3125D, 0.822D, 0.822D);

    public AmethystBud(String name, MapColor mapColor) {
        super(name, mapColor);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.AIR;
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        AxisAlignedBB AABB = UP_AABB;
        switch(state.getValue(FACING).getIndex())
        {
            case 0: AABB = DOWN_AABB; break;
            case 1: AABB = UP_AABB; break;
            case 2: AABB = NORTH_AABB; break;
            case 3: AABB = SOUTH_AABB; break;
            case 4: AABB = WEST_AABB; break;
            case 5: AABB = EAST_AABB; break;
        }
        return AABB;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        AxisAlignedBB AABB = UP_AABB;
        switch(blockState.getValue(FACING).getIndex())
        {
            case 0: AABB = DOWN_AABB; break;
            case 1: AABB = UP_AABB; break;
            case 2: AABB = NORTH_AABB; break;
            case 3: AABB = SOUTH_AABB; break;
            case 4: AABB = WEST_AABB; break;
            case 5: AABB = EAST_AABB; break;
        }
        return AABB;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { return 1; }
}
