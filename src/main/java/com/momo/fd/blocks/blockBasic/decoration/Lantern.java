package com.momo.fd.blocks.blockBasic.decoration;


import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundEvent;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.Random;

public class Lantern extends Block implements IHasModel {
    public static final PropertyBool HANGING = PropertyBool.create("hanging");

    protected static final AxisAlignedBB HANGING_AABB = new AxisAlignedBB(0.3125F, 0.06F, 0.3125F, 0.6875F, 0.6225F, 0.6875F);
    protected static final AxisAlignedBB NROMAL_AABB = new AxisAlignedBB(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.5625F, 0.6875F);

    public static final SoundType LANTERN = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_LANTER_BREAK, ModSoundHandler.BLOCK_LANTER_STEP, ModSoundHandler.BLOCK_LANTER_PLACE, ModSoundHandler.BLOCK_LANTER_HIT, ModSoundHandler.BLOCK_LANTER_FALL);

    public Lantern(String name)
    {
        super(Material.IRON);

        setUnlocalizedName(name);
        setRegistryName(name);

        setHardness(2.0F);
        setResistance(4.0F);
        setLightLevel(1.0F);
        setSoundType(LANTERN);
        setCreativeTab(CreativeTabs.DECORATIONS);
        this.setTickRandomly(true);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        setDefaultState(blockState.getBaseState().withProperty(HANGING, false));
    }

    @Override
    public void registerModels()
    { MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");}

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        EnumFacing direction = EnumFacing.UP;

<<<<<<< HEAD
        if(worldIn.getBlockState(pos).getValue(HANGING))
        {
            direction = EnumFacing.DOWN;
        }

        if (!canPlaceBlock(worldIn, pos, direction))
=======
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        boolean hanging = worldIn.getBlockState(pos).getValue(HANGING);
        if (hanging && worldIn.isAirBlock(pos.up()) || !hanging && worldIn.isAirBlock(pos.down()))
>>>>>>> parent of fc0dc3f (1)
        {
            state.getBlock().dropBlockAsItem(worldIn, pos, state, 1);
            worldIn.setBlockToAir(pos);
        }
    }

<<<<<<< HEAD
    protected static boolean canPlaceBlock(World worldIn, BlockPos pos, EnumFacing direction)
    {
        BlockPos blockpos = pos.offset(direction.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        boolean flag = iblockstate.getBlockFaceShape(worldIn, blockpos, direction) == BlockFaceShape.SOLID;

        Block block = iblockstate.getBlock();

        if(block instanceof Chain)
        {
            EnumFacing facing = iblockstate.getValue(Chain.FACING);
            if(facing == EnumFacing.UP || facing == EnumFacing.DOWN)
            {
                flag = true;
            }
        }

        if(block instanceof Bar || block == Blocks.IRON_BARS || block instanceof BlockWall || block instanceof BlockFence)
        {
            flag = true;
        }

       return flag;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            if(enumfacing == EnumFacing.UP || enumfacing == EnumFacing.DOWN)
            {
                return canPlaceBlock(worldIn, pos, enumfacing);
            }
        }
        return false;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
    {
        if(side.equals(EnumFacing.DOWN) || side.equals(EnumFacing.UP))
        {
            return canPlaceBlock(world, pos, side);
        }else
            return false;
    }

=======
>>>>>>> parent of fc0dc3f (1)
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(HANGING) ? 1 : 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(HANGING, (meta & 1) > 0);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, HANGING);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        if (facing.equals(EnumFacing.DOWN)) { return this.getDefaultState().withProperty(HANGING, true); }
        return this.getDefaultState().withProperty(HANGING, false);
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
    {
        return side.equals(EnumFacing.DOWN) || side.equals(EnumFacing.UP);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.CENTER;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if (state.getValue(HANGING)) {
            return HANGING_AABB;
        }
        else {
            return NROMAL_AABB;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return getBoundingBox(blockState, worldIn, pos);
    }
}
