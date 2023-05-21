package com.momo.fd.blocks.blockMisc;

import com.momo.fd.blocks.BlockBase;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.Random;

public class RootedDirt extends BlockBase implements IGrowable {

    public static final SoundType ROOTED_DIRT = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_ROOTED_DIRT_BREAK, ModSoundHandler.BLOCK_ROOTED_DIRT_STEP, ModSoundHandler.BLOCK_ROOTED_DIRT_PLACE, ModSoundHandler.BLOCK_ROOTED_DIRT_HIT, ModSoundHandler.BLOCK_ROOTED_DIRT_FALL);

    public RootedDirt() {
        super("rooted_dirt", Material.GROUND, MapColor.DIRT);

        setHardness(0.5F);
        setSoundType(ROOTED_DIRT);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }


    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemStack = playerIn.getHeldItem(hand);
        if(itemStack.getItem() instanceof ItemHoe)
        {
            worldIn.playSound(playerIn, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            playerIn.swingArm(hand);

            worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), 11);
            itemStack.damageItem(1, playerIn);

            if(!worldIn.isRemote)
            {
                worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.HANGING_ROOTS)));
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) { return true; }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) { return true; }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if(worldIn.isAirBlock(pos.down()) || worldIn.getBlockState(pos.down()).getBlock().isReplaceable(worldIn, pos.down()))
        {
            worldIn.setBlockState(pos.down(), ModBlocks.HANGING_ROOTS.getDefaultState(), 11);
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable) {
        if(super.canSustainPlant(state, world, pos, direction, plantable)) {
            return true;
        }

        EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch(plantType) {
            case Beach:
                boolean hasWater = (world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                        world.getBlockState(pos.south()).getMaterial() == Material.WATER);
                return hasWater;
            case Plains:
                return true;
            default:
                return false;
        }
    }
}
