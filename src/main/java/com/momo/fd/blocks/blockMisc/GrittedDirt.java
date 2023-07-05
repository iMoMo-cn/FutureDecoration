package com.momo.fd.blocks.blockMisc;

import com.momo.fd.blocks.BlockBase;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GrittedDirt extends BlockBase {
    public GrittedDirt() {
        super("gritted_dirt", Material.GROUND);

        setHardness(0.5F);
        setHarvestLevel("spade", 0);

        setSoundType(SoundType.GROUND);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (fortune > 3)
        {
            fortune = 3;
        }

        return rand.nextInt(20 - fortune * 6) == 0 ? Items.FLINT : super.getItemDropped(state, rand, fortune);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(playerIn.getHeldItem(hand).getItem() instanceof ItemHoe)
        {
            if(!worldIn.isRemote)
            {
                worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT));
                playerIn.getHeldItem(hand).damageItem(1, playerIn);
            }

            worldIn.playSound(playerIn, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

            return true;
        }

        return false;
    }


}
