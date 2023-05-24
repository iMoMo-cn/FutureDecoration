package com.momo.fd.blocks.blockMisc.crystal;

import com.momo.fd.blocks.BlockBase;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AmethystBlock extends BlockBase {
    public static final SoundType AMETHYST = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_AMETHYST_BREAK, ModSoundHandler.BLOCK_AMETHYST_STEP, ModSoundHandler.BLOCK_AMETHYST_PLACE, ModSoundHandler.BLOCK_AMETHYST_HIT, ModSoundHandler.BLOCK_AMETHYST_FALL);

    public AmethystBlock(String name, MapColor color) {
        super(name, Material.ROCK, color);

        setHardness(1.0F);
        setResistance(5.0F);

        setSoundType(AMETHYST);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(worldIn.isRemote)
        {
            worldIn.playSound(playerIn, pos, ModSoundHandler.BLOCK_AMETHYST_CHIME, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
        }
        return false;
    }

    @Deprecated
    public boolean canEntitySpawn(IBlockState state, Entity entityIn)
    {
        return false;
    }
}
