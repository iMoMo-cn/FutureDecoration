package com.momo.fd.blocks.blockInteractive;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockTile.TileEntityComposter;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundEvent;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class Composter extends BlockContainer implements IHasModel {

    public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 8);

    public Composter(String name)
    {
        super(Material.WOOD);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        this.setHardness(2.5F);
        this.setResistance(2.5F);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(CreativeTabs.DECORATIONS);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this){
            @Override
            public int getItemBurnTime(ItemStack itemStack)
            {
                return 300;
            }
        }.setRegistryName(this.getRegistryName()));

        this.setDefaultState(blockState.getBaseState().withProperty(LEVEL, 0));
        this.setTickRandomly(true);

    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        boolean added = false;
        TileEntity tile = world.getTileEntity(pos);
        if (tile != null && tile.getClass() == TileEntityComposter.class) {
            TileEntityComposter composter = (TileEntityComposter)tile;
            added = composter.addItem(player.getHeldItem(hand), player.isCreative());

            if (added && world.isRemote) {
                spawnParticles(world, pos);
            }
        }
        return added;
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) { return new TileEntityComposter(); }


    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World world, BlockPos pos)
    {
        return blockState.getValue(LEVEL);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, LEVEL);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(LEVEL);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(LEVEL, meta);
    }

    @SideOnly(Side.CLIENT)
    public void spawnParticles(World world, BlockPos pos)
    {
        for (int i = 0; i < 8; ++i) {
            double d0 = world.rand.nextGaussian() * 0.02D;
            double d1 = world.rand.nextGaussian() * 0.02D;
            double d2 = world.rand.nextGaussian() * 0.02D;
            double dx = (float)pos.getX() + MathHelper.clamp(world.rand.nextFloat(), 0.25f, 0.75f);
            double dy = (float)pos.getY() + 1.05f;
            double dz = (float)pos.getZ() + MathHelper.clamp(world.rand.nextFloat(), 0.25f, 0.75f);
            world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, dx, dy, dz, d0, d1, d2);
        }
    }
}
