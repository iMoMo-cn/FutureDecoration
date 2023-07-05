package com.momo.fd.blocks.blockColor;

import com.momo.fd.blocks.ModBlocks;
import net.minecraft.block.BlockStem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class BlockColorRegister {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColor(ColorHandlerEvent.Block event)
    {
        //find color multiplies in BlockColors.java
        event.getBlockColors().registerBlockColorHandler(new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
            {
                int i = ((Integer)state.getValue(BlockStem.AGE)).intValue();
                int j = i * 32;
                int k = 255 - i * 8;
                int l = i * 4;

                return  j << 16 | k << 8 | l;
            }
        }, ModBlocks.PUMPKIN_STEM
        );

        event.getBlockColors().registerBlockColorHandler(new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
            {
                return worldIn != null && pos != null ? 2129968 : 7455580;
            }
            }, ModBlocks.LOTUS
        );
    }
}
