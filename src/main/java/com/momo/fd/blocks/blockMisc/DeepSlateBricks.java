package com.momo.fd.blocks.blockMisc;

import com.momo.fd.blocks.blockVariant.baseVariant.BlockVariantBase;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class DeepSlateBricks extends BlockVariantBase {
    public static final SoundType DEEPSLATE_BRICKS = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_DEEPSLATE_BRICKS_BREAK, ModSoundHandler.BLOCK_DEEPSLATE_BRICKS_STEP, ModSoundHandler.BLOCK_DEEPSLATE_BRICKS_PLACE, ModSoundHandler.BLOCK_DEEPSLATE_BRICKS_HIT, ModSoundHandler.BLOCK_DEEPSLATE_BRICKS_FALL);


    public DeepSlateBricks(int maxUsedVariants) {
        super("deepslate_rock", Material.ROCK, MapColor.GRAY, maxUsedVariants);
        setHardness(2.0F);
        setResistance(10.0F);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        setSoundType(DEEPSLATE_BRICKS);
    }


}
