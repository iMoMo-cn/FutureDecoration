package com.momo.fd.blocks.blockBasic.decoration;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Chain extends Block implements IHasModel {

    protected static final AxisAlignedBB X_AABB = new AxisAlignedBB(0.0F, 0.40625F, 0.40625F, 1.0F, 0.59375F, 0.59375F);
    protected static final AxisAlignedBB Y_AABB = new AxisAlignedBB(0.40625F, 0.0F, 0.40625F, 0.59375F, 1.0F, 0.59375F);
    protected static final AxisAlignedBB Z_AABB = new AxisAlignedBB(0.40625F, 0.40625F, 0.0F, 0.59375F, 0.59375F, 1.0F);

    public static final PropertyDirection FACING = PropertyDirection.create("facing");

    public static final SoundType CHAIN = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_CHAIN_BREAK, ModSoundHandler.BLOCK_CHAIN_STEP, ModSoundHandler.BLOCK_CHAIN_PLACE, ModSoundHandler.BLOCK_CHAIN_HIT, ModSoundHandler.BLOCK_CHAIN_FALL);

    public Chain(String name, Material material){
        super(material);

        setUnlocalizedName(name);
        setRegistryName(name);

        if(material == Material.PLANTS)
        {
            setSoundType(SoundType.PLANT);
            setHardness(0.3F);
        }else {
            setSoundType(CHAIN);
            setHardness(5.0F);
            setResistance(4.0F);
        }

        setCreativeTab(CreativeTabs.DECORATIONS);

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
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

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing);
    }

    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();
        iblockstate = iblockstate.withProperty(FACING, EnumFacing.getFront(meta));
        return iblockstate;
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    public EnumPushReaction getMobilityFlag(IBlockState state)
    {
        return EnumPushReaction.NORMAL;
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withProperty(FACING, mirrorIn.mirror((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (((EnumFacing)state.getValue(FACING)).getAxis())
        {
            case X:
            default:
                return X_AABB;
            case Z:
                return Z_AABB;
            case Y:
                return Y_AABB;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return getBoundingBox(blockState, worldIn, pos);
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
