package com.momo.fd.blocks.blockBasic;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class Slab extends BlockSlab implements IHasModel {
    public static final PropertyEnum<Slab.Variant> VARIANT = PropertyEnum.<Slab.Variant>create("variant", Slab.Variant.class);

    public Slab(String name, MapColor mapColor)
    {
        super(Material.ROCK, mapColor);
        IBlockState iblockstate = this.blockState.getBaseState();
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate.withProperty(VARIANT, Slab.Variant.DEFAULT));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, Slab.Variant.DEFAULT);

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

    /**
     * Returns the slab block name with the type associated with it
     */
    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if (this.isDouble())
        {
            return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
        else if (side != EnumFacing.UP && side != EnumFacing.DOWN && !super.shouldSideBeRendered(blockState, blockAccess, pos, side))
        {
            return false;
        }
        else if (false) // Forge: Additional logic breaks doesSideBlockRendering and is no longer useful.
        {
            IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
            boolean flag = isHalfSlab(iblockstate) && iblockstate.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP;
            boolean flag1 = isHalfSlab(blockState) && blockState.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP;

            if (flag1)
            {
                if (side == EnumFacing.DOWN)
                {
                    return true;
                }
                else if (side == EnumFacing.UP && super.shouldSideBeRendered(blockState, blockAccess, pos, side))
                {
                    return true;
                }
                else
                {
                    return !isHalfSlab(iblockstate) || !flag;
                }
            }
            else if (side == EnumFacing.UP)
            {
                return true;
            }
            else if (side == EnumFacing.DOWN && super.shouldSideBeRendered(blockState, blockAccess, pos, side))
            {
                return true;
            }
            else
            {
                return !isHalfSlab(iblockstate) || flag;
            }
        }
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    @SideOnly(Side.CLIENT)
    protected static boolean isHalfSlab(IBlockState state) { return true; }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return Item.getItemFromBlock(this); }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) { return new ItemStack(this); }

    @Override
    public boolean isDouble() { return false; }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return Slab.Variant.DEFAULT;
    }

    public static enum Variant implements IStringSerializable
    {
        DEFAULT;

        public String getName()
        {
            return "default";
        }
    }
}
