package com.momo.fd.events;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ItemDropEvent {
    @SubscribeEvent
    public static void WitherRose(LivingDeathEvent event)
    {
        EntityLivingBase deadOne = event.getEntityLiving();
        World world = deadOne.world;

        if(event.getSource().getTrueSource() instanceof EntityWither)
        {
            BlockPos deadPos = new BlockPos(deadOne.posX, deadOne.posY, deadOne.posZ);

            if (ModBlocks.WITHER_ROSE.canPlaceBlockAt(world, deadPos)) {
                world.setBlockState(deadPos, ModBlocks.WITHER_ROSE.getDefaultState(), 2);
            }
            else
                {
                    deadOne.dropItem(Item.getItemFromBlock(ModBlocks.WITHER_ROSE), 1);
                }
        }
    }
}
