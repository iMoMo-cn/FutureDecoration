package com.momo.fd.blocks.blockMisc;

import com.momo.fd.blocks.BlockBase;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Random;

public class DeepSlate extends BlockBase {
    public static final SoundType DEEPSLATE = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_DEEPSLATE_BREAK, ModSoundHandler.BLOCK_DEEPSLATE_STEP, ModSoundHandler.BLOCK_DEEPSLATE_PLACE, ModSoundHandler.BLOCK_DEEPSLATE_HIT, ModSoundHandler.BLOCK_DEEPSLATE_FALL);

    public DeepSlate() {
        super("deepslate", Material.ROCK, MapColor.GRAY);
        setHardness(3.0F);
        setResistance(6.0F);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setSoundType(DEEPSLATE);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.DEEPSLATE_ROCK);
    }

}
