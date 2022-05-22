package com.momo.fd.events;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.Reference;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class WoodStrippedEvent {
    @SubscribeEvent
    public static void WoodStripped(PlayerInteractEvent.RightClickBlock evt)
    {
        World world = evt.getWorld();
        BlockPos pos = evt.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        EntityPlayer player = evt.getEntityPlayer();
        ItemStack tool = evt.getItemStack();


        if (tool.getItem() instanceof ItemAxe) {
            if(block instanceof BlockLog)
            {

                if(block instanceof BlockOldLog)
                {
                    BlockPlanks.EnumType blockplanks$enumtype = (BlockPlanks.EnumType)state.getValue(BlockOldLog.VARIANT);
                    BlockLog.EnumAxis blocklog$enumaxis = (BlockLog.EnumAxis)state.getValue(BlockLog.LOG_AXIS);

                    switch (blockplanks$enumtype)
                    {
                        case BIRCH:
                            world.setBlockState(pos, ModBlocks.STRIPPED_BIRCH.getDefaultState().withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
                            toolDamage(player, tool);
                            world.playSound(player, pos, ModSoundHandler.STRIP_WOOD, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            return;
                        case JUNGLE:
                            world.setBlockState(pos, ModBlocks.STRIPPED_JUNGLE.getDefaultState().withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
                            toolDamage(player, tool);
                            world.playSound(player, pos, ModSoundHandler.STRIP_WOOD, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            return;
                        case OAK:
                            world.setBlockState(pos, ModBlocks.STRIPPED_OAK.getDefaultState().withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
                            toolDamage(player, tool);
                            world.playSound(player, pos, ModSoundHandler.STRIP_WOOD, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            return;
                        case SPRUCE:
                            world.setBlockState(pos, ModBlocks.STRIPPED_SPRUCE.getDefaultState().withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
                            toolDamage(player, tool);
                            world.playSound(player, pos, ModSoundHandler.STRIP_WOOD, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            return;
                    }
                }
                else if(block instanceof BlockNewLog)
                {
                    BlockPlanks.EnumType blockplanks$enumtype = (BlockPlanks.EnumType)state.getValue(BlockNewLog.VARIANT);
                    BlockLog.EnumAxis blocklog$enumaxis = (BlockLog.EnumAxis)state.getValue(BlockLog.LOG_AXIS);

                    switch (blockplanks$enumtype)
                    {
                        case ACACIA:
                            world.setBlockState(pos, ModBlocks.STRIPPED_ACACIA.getDefaultState().withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
                            toolDamage(player, tool);
                            world.playSound(player, pos, ModSoundHandler.STRIP_WOOD, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            return;
                        case DARK_OAK:
                            world.setBlockState(pos, ModBlocks.STRIPPED_DARK_OAK.getDefaultState().withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
                            toolDamage(player, tool);
                            world.playSound(player, pos, ModSoundHandler.STRIP_WOOD, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            return;
                    }
                }
            }
        }
    }

    public static void toolDamage(EntityPlayer player, ItemStack tool)
    {
        player.swingArm(EnumHand.MAIN_HAND);

        if(!player.capabilities.isCreativeMode)
        {
            tool.setItemDamage(tool.getItemDamage() + 1);
        }
    }
}
