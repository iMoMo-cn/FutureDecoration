package com.momo.fd.blocks.blockBush.berries;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class SavannaBerriBush extends BerriesBush {
    public SavannaBerriBush(String name) {
        super(name);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModItems.SOUR_BERRIES);
    }

    protected Item getSeed()
    {
        return ModItems.SOUR_BERRIES;
    }

    protected Item getCrop()
    {
        return ModItems.SOUR_BERRIES;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        int age = getAge(state);
        Random rand = new Random();
        boolean flag1 = playerIn.getHeldItemOffhand().getItem() == (new ItemStack(Items.DYE, 1, 0)).getItem();
        boolean flag2 = playerIn.getHeldItemMainhand().getItem() == (new ItemStack(Items.DYE, 1, 0)).getItem();
        boolean flag3 = playerIn.getHeldItemOffhand().getItem() instanceof ItemBlock;
        boolean flag4 = playerIn.getHeldItemOffhand().getItem() instanceof ItemBlock;

        boolean flag = flag1 || flag2 || flag3 || flag4;

        if(age > 2)
        {
            flag = false;
        }

        if(!flag && age >= 2)
        {
            if (!worldIn.isRemote)
            {
                worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.SOUR_BERRIES, rand.nextInt(2) + age - 1)));
                worldIn.setBlockState(pos, ModBlocks.SAVANNA_BERRY_BUSH.getDefaultState().withProperty(BERRIES_AGE, 1));
            }

            if (worldIn.isRemote)
            {
                worldIn.playSound(playerIn, pos, ModSoundHandler.PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            }
        }
        return false;
    }
}
