package com.momo.fd.blocks.blockMisc.carpet;

import com.momo.fd.blocks.blockBasic.decoration.Carpet;
import com.momo.fd.blocks.blockMisc.MossBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class MossCarpet extends Carpet {

    public MossCarpet(int maxUsedVariants) {
        super("moss_carpet", Material.PLANTS, Material.PLANTS.getMaterialMapColor(), maxUsedVariants);

        setSoundType(SoundType.PLANT);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity)
    {
        if (state.getValue(VARIANT).getMeta() == 0)  return MossBlock.MOSS;
        return getSoundType();
    }
}
