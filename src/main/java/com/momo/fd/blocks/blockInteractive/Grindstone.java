package com.momo.fd.blocks.blockInteractive;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
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
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Grindstone extends Block implements IHasModel
{
    public static final PropertyDirection FACING = BlockDirectional.FACING;
    public static final PropertyDirection FACE = PropertyDirection.create("face", EnumFacing.Plane.HORIZONTAL);

    protected static final AxisAlignedBB UP_DOWN = new AxisAlignedBB(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
    protected static final AxisAlignedBB EAST_WEST = new AxisAlignedBB(0.0F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
    protected static final AxisAlignedBB SOUTH_NORTH = new AxisAlignedBB(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 1.0F);

    public Grindstone(String name){
        super(Material.ROCK, MapColor.STONE);
        setUnlocalizedName(name);
        setRegistryName(name);

        this.setHardness(2.0F);
        this.setResistance(6.0F);
        this.setHarvestLevel("pickaxe", 0);
        this.setSoundType(SoundType.STONE);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(FACE, EnumFacing.NORTH));
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

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


    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, facing).withProperty(FACE, placer.getHorizontalFacing());
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, FACE});
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     * The Index of this Facing (0-5). The order is D-U-N-S-W-E
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState();

        switch(meta)
        {
            case 0 :
            default:
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP).withProperty(FACE, EnumFacing.NORTH); break;
            case 1 :
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.DOWN).withProperty(FACE, EnumFacing.NORTH); break;
            case 2 :
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP).withProperty(FACE, EnumFacing.WEST); break;
            case 3 :
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.DOWN).withProperty(FACE, EnumFacing.WEST); break;
            case 4 :
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH).withProperty(FACE, EnumFacing.WEST); break;
            case 5 :
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH).withProperty(FACE, EnumFacing.WEST); break;
            case 6 :
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST).withProperty(FACE, EnumFacing.WEST); break;
            case 7 :
                iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST).withProperty(FACE, EnumFacing.WEST); break;
        }
        return iblockstate;
    }

    public int getMetaFromState(IBlockState state)
    {
        int i = ((EnumFacing)state.getValue(FACE)).getHorizontalIndex(); //The order is S-W-N-E
        int j = ((EnumFacing)state.getValue(FACING)).getIndex(); //The order is D-U-N-S-W-E
        if(i == 0 || i == 2)
        {
            switch (j)
            {
                case 0 :
                    return 1;
                case 1 :
                    return 0;
                case 2 :
                    return 4;
                case 3 :
                    return 5;
                case 4 :
                    return 6;
                case 5 :
                    return 7;
            }
        }
        else if(i == 1 || i ==3){
            switch (j)
            {
                case 0 :
                    return 3;
                case 1 :
                    return 2;
                case 2 :
                    return 4;
                case 3 :
                    return 5;
                case 4 :
                    return 6;
                case 5 :
                    return 7;
            }
        }
        return 0;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch ((EnumFacing)state.getValue(FACING))
        {
            case UP : case DOWN :
            default:
                return UP_DOWN;
            case EAST : case WEST :
                return EAST_WEST;
            case SOUTH : case NORTH :
                return SOUTH_NORTH;
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
