package com.momo.fd.blocks.blockBush;

import com.momo.fd.blocks.ModBlocks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class CaveVine extends CaveVinePlant {

    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 1);

    public CaveVine(String name) {
        super(name);
        this.setTickRandomly(true);

        this.setDefaultState(this.getDefaultState().withProperty(BERRIES, false).withProperty(AGE, 0));
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        super.updateTick(worldIn, pos, state, rand);

        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light

        IBlockState iblockstate = worldIn.getBlockState(pos.down());

        if(iblockstate.getBlock().isAir(iblockstate, worldIn, pos.down()))
        {
            if (this.getAge(state) > 0)
            {
                float f = 2.0F;

                int i = checkCanGrow(worldIn, pos);

                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int)(25.0F / f) + 1) == 0))
                {
                    worldIn.setBlockState(pos, ModBlocks.CAVE_VINE_PLANT.getDefaultState().withProperty(CaveVinePlant.BERRIES, state.getValue(BERRIES)), 2);

                    if(rand.nextFloat() < 0.22F)
                    {
                        worldIn.setBlockState(pos.down(), this.withAge(i).withProperty(BERRIES, true), 2);
                    } else{
                        worldIn.setBlockState(pos.down(), this.withAge(i).withProperty(BERRIES, false), 2);
                    }

                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
                }
            }
        }
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        worldIn.setBlockState(pos, this.getDefaultState().withProperty(BERRIES, true).withProperty(AGE, state.getValue(AGE)), 2);
    }

    protected int getAge(IBlockState state)
    {
        return state.getValue(AGE);
    }

    public IBlockState withAge(int age)
    {
        return this.getDefaultState().withProperty(AGE, age);
    }

    protected int checkCanGrow(World world, BlockPos pos)
    {
        Random r = new Random();

        int Max = 2 + r.nextInt(11);

        int Num = 0;

        for(int i = 1; i < 13; i++)
        {
            if(world.getBlockState(pos.add(0, i, 0)).getBlock() == ModBlocks.CAVE_VINE_PLANT)
            {
                Num += 1;
            }
            else
                break;
        }

        if(Num < Max) { return 1; }

        return 0;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        if(playerIn.getHeldItem(hand).getItem() == Items.SHEARS && state.getValue(AGE) == 1)
        {
            if(!worldIn.isRemote)
            {
                worldIn.setBlockState(pos, state.withProperty(AGE, 0));
            }
        }

        super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitX, hitZ);

        return false;
    }


    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        if(meta == 1)
            return this.getDefaultState().withProperty(BERRIES, false).withProperty(AGE, 1);

        if(meta == 2)
            return this.getDefaultState().withProperty(BERRIES, true).withProperty(AGE, 0);

        if(meta == 3)
            return this.getDefaultState().withProperty(BERRIES, true).withProperty(AGE, 1);

        return this.getDefaultState().withProperty(BERRIES, false).withProperty(AGE, 0);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = state.getValue(AGE);

        if(state.getValue(BERRIES))
            return 2 + i;
        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BERRIES, AGE});
    }
}
