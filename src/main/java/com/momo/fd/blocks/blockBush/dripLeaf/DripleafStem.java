package com.momo.fd.blocks.blockBush.dripLeaf;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class DripleafStem extends BlockBush implements IHasModel, IGrowable
{
    public static final SoundType DRIPLEAF = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_BIG_DRIPLEAF_BREAK, ModSoundHandler.BLOCK_BIG_DRIPLEAF_STEP, ModSoundHandler.BLOCK_BIG_DRIPLEAF_PLACE, ModSoundHandler.BLOCK_BIG_DRIPLEAF_HIT, ModSoundHandler.BLOCK_BIG_DRIPLEAF_FALL);

    public DripleafStem()
    {
        super(Material.VINE);

        setHardness(0.0F);

        setUnlocalizedName("big_dripleaf_stem");
        setRegistryName("big_dripleaf_stem");

        setSoundType(DRIPLEAF);

        ModBlocks.BLOCKS.add(this);

        setDefaultState(this.blockState.getBaseState());

    }

    @Override
    public void registerModels() {  }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { return FULL_BLOCK_AABB; }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());

        boolean flag1 = soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);

        boolean flag2 = soil.getBlock() == this;

        boolean flag3 = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);

        return (flag1 || flag2) && flag3;
    }

//    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
//    {
//        IBlockState top = worldIn.getBlockState(pos.up());
//
//        return top.getBlock() == this || top.getBlock() == ModBlocks.BIG_DRIPLEAF;
//    }
//
//    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return Item.getItemFromBlock(ModBlocks.BIG_DRIPLEAF); }
//
//    @Deprecated // Forge: Use more sensitive version below: getPickBlock
//    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) { return new ItemStack(ModBlocks.BIG_DRIPLEAF); }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) { return  true; }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) { return true; }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {

    }
}
