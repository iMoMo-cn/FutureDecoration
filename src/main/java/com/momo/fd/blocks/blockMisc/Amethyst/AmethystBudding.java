package com.momo.fd.blocks.blockMisc.Amethyst;

import com.momo.fd.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.momo.fd.blocks.blockMisc.Amethyst.AmethystCluster.FACING;

public class AmethystBudding extends AmethystBlock {
    public AmethystBudding(String name) {
        super(name);

        this.setTickRandomly(true);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.AMETHYST_BLOCK);
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
                worldIn.setBlockState(pos.offset(enumfacing.getOpposite()), ModBlocks.AMETHYST_SMALL_BUD.getDefaultState().withProperty(FACING, enumfacing.getOpposite()), 2);
            }
            else if(block == ModBlocks.AMETHYST_SMALL_BUD)
            {
                worldIn.setBlockState(pos.offset(enumfacing.getOpposite()), ModBlocks.AMETHYST_MEDIUM_BUD.getDefaultState().withProperty(FACING, enumfacing.getOpposite()), 2);
            }
            else if(block == ModBlocks.AMETHYST_MEDIUM_BUD)
            {
                worldIn.setBlockState(pos.offset(enumfacing.getOpposite()), ModBlocks.AMETHYST_LARGE_BUD.getDefaultState().withProperty(FACING, enumfacing.getOpposite()), 2);
            }
            else if(block == ModBlocks.AMETHYST_LARGE_BUD)
            {
                worldIn.setBlockState(pos.offset(enumfacing.getOpposite()), ModBlocks.AMETHYST_CLUSTERS.getDefaultState().withProperty(FACING, enumfacing.getOpposite()), 2);
            }

            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
        }
    }

    public boolean isBlockAir(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock().isAir(worldIn.getBlockState(pos), worldIn, pos);
    }

}
