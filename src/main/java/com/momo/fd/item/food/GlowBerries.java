package com.momo.fd.item.food;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockBush.CaveVine;
import com.momo.fd.blocks.blockBush.CaveVinePlant;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class GlowBerries extends ItemFood implements IHasModel, net.minecraftforge.common.IPlantable {

    public GlowBerries(String name, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        MoMoFramework.proxy.registerItemRenderer(this, 0, "inventory");
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.DOWN && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.down()))
        {
            if(state.getBlock() == ModBlocks.CAVE_VINE)
            {
                worldIn.setBlockState(pos, ModBlocks.CAVE_VINE_PLANT.getDefaultState().withProperty(CaveVinePlant.BERRIES, state.getValue(CaveVinePlant.BERRIES)));
                worldIn.setBlockState(pos.down(), ModBlocks.CAVE_VINE.getDefaultState().withProperty(CaveVine.AGE, 1));

                if(!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }

                if(worldIn.isRemote)
                {
                    worldIn.playSound(player, pos, ModSoundHandler.BLOCK_CAVE_VINES_PLACE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
                }

                return EnumActionResult.SUCCESS;
            }
            else if(state.isOpaqueCube() || state.getBlock() == ModBlocks.CAVE_VINE_PLANT)
            {
                worldIn.setBlockState(pos.down(), ModBlocks.CAVE_VINE.getDefaultState().withProperty(CaveVine.AGE, 1));

                if (!player.capabilities.isCreativeMode)
                {
                    itemstack.shrink(1);
                }

                if(worldIn.isRemote)
                {
                    worldIn.playSound(player, pos, ModSoundHandler.BLOCK_CAVE_VINES_PLACE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
                }

                return EnumActionResult.SUCCESS;
            }

            return EnumActionResult.FAIL;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return ModBlocks.CAVE_VINE.getDefaultState();
    }
}
