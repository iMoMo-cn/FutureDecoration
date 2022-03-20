package com.momo.fd.events;

import com.momo.fd.blocks.blockBasic.Slab;
import com.momo.fd.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class SlabDropEvent {
    @SubscribeEvent
    public static void onSlabDistory(BlockEvent.HarvestDropsEvent event){
        Block block = event.getState().getBlock();
        if(block instanceof Slab){
            event.getDrops().add(new ItemStack(block));
        }
    }
}
