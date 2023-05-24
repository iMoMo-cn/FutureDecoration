package com.momo.fd.blocks.blockMisc.crystal;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.momo.fd.blocks.blockMisc.crystal.AmethystCluster.FACING;

public class AmethystBudding extends AmethystBlock {
    private Block block, smallBud, mediumBud, largeBud, cluster;

    public AmethystBudding(String name, MapColor color, Block block, Block smallBud, Block mediumBud, Block largeBud, Block cluster) {
        super(name, color);

        this.setTickRandomly(true);

        this.block = block;
        this.smallBud = smallBud;
        this.mediumBud = mediumBud;
        this.largeBud = largeBud;
        this.cluster = cluster;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(block);
    }

    @Deprecated //Forge: State sensitive version
    protected boolean canSilkHarvest() { return false; }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(5) == 0))
        {
            EnumFacing enumfacing = EnumFacing.random(rand);

            Block block = worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getBlock();

            if(isBlockAir(worldIn, pos.offset(enumfacing.getOpposite())))
            {
                worldIn.setBlockState(pos.offset(enumfacing.getOpposite()), smallBud.getDefaultState().withProperty(FACING, enumfacing.getOpposite()), 2);
            }
            else if(block == smallBud)
            {
                worldIn.setBlockState(pos.offset(enumfacing.getOpposite()), mediumBud.getDefaultState().withProperty(FACING, enumfacing.getOpposite()), 2);
            }
            else if(block == mediumBud)
            {
                worldIn.setBlockState(pos.offset(enumfacing.getOpposite()), largeBud.getDefaultState().withProperty(FACING, enumfacing.getOpposite()), 2);
            }
            else if(block == largeBud)
            {
                worldIn.setBlockState(pos.offset(enumfacing.getOpposite()), cluster.getDefaultState().withProperty(FACING, enumfacing.getOpposite()), 2);
            }

            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
        }
    }

    public boolean isBlockAir(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock().isAir(worldIn.getBlockState(pos), worldIn, pos);
    }

}
