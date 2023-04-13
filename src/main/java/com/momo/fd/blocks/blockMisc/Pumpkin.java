package com.momo.fd.blocks.blockMisc;

import com.momo.fd.blocks.BlockBase;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.Reference;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.minecraft.block.BlockHorizontal.FACING;

public class Pumpkin extends BlockBase {
    public Pumpkin() {
        super("pumpkin", Material.GOURD, MapColor.ADOBE);
        setHardness(1.0F);
        setSoundType(SoundType.WOOD);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @SubscribeEvent
    public static void PumpkinCarve(PlayerInteractEvent.RightClickBlock evt)
    {
        World world = evt.getWorld();
        BlockPos pos = evt.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        EntityPlayer player = evt.getEntityPlayer();
        ItemStack tool = evt.getItemStack();

        if (tool.getItem() instanceof ItemShears) {
            if(block == ModBlocks.PUMPKIN)
            {
                EnumFacing blockFace = player.getHorizontalFacing().getOpposite();

                world.setBlockState(pos, Blocks.PUMPKIN.getDefaultState().withProperty(FACING, blockFace));

                if(!world.isRemote)
                {
                    world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(Items.PUMPKIN_SEEDS, 4)));
                }

                player.swingArm(EnumHand.MAIN_HAND);

                if(!player.capabilities.isCreativeMode)
                {
                    tool.setItemDamage(tool.getItemDamage() + 1);
                }

                if(world.isRemote)
                {
                    world.playSound(player, pos, ModSoundHandler.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
        }
    }
}
