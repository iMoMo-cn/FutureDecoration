package com.momo.fd.blocks.blockBasic.decoration;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.Block;
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
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Slab extends BlockSlab implements IHasModel {
    private boolean isDouble;
    public Block dropBlock;
    private BlockSlab doubleSlab;

    public static final PropertyEnum<Slab.Variant> VARIANT = PropertyEnum.<Slab.Variant>create("variant", Slab.Variant.class);

    public Slab(String name, MapColor mapColor)
    {
        this(name, true, mapColor, null);
    }

    public Slab(String name, MapColor mapColor, BlockSlab doubleSlab)
    {
        this(name, false, mapColor, doubleSlab);
    }

    public Slab(String name, boolean isDouble, MapColor mapColor, BlockSlab doubleSlab)
    {
        super(Material.ROCK, mapColor);
        this.setResistance(10.0F);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.STONE);

        this.doubleSlab = doubleSlab;
        this.isDouble = isDouble;
        this.setUnlocalizedName(name);
        this.setRegistryName(name);

        ModBlocks.BLOCKS.add(this);

        ItemBlock itemBlock = new ItemSlab(this, this, doubleSlab);

        IBlockState iblockstate = this.blockState.getBaseState();
        if (!this.isDouble())
        {
            this.dropBlock = this;
            iblockstate = iblockstate.withProperty(HALF, EnumBlockHalf.BOTTOM);
            ModItems.ITEMS.add(itemBlock.setRegistryName(name));
        }

        this.setDefaultState(iblockstate.withProperty(VARIANT, Slab.Variant.DEFAULT));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        useNeighborBrightness = true;
    }

    @Override
    public void registerModels() {
        if(!this.isDouble()) MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }


    public Slab setDropped(Block dropBlockIn){
        this.dropBlock = dropBlockIn;
        return this;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName();
    }

    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    @Override
    public boolean isDouble() { return isDouble; }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return Slab.Variant.DEFAULT;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
//        return isDouble() ? 0 : state.getValue(HALF).ordinal() + 1;
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
//      return !isDouble() ? getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]) : getDefaultState();

        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, Slab.Variant.DEFAULT);
        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }
        return iblockstate;
    }


    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(dropBlock);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        if(this.isDouble()){
            drops.add(new ItemStack(dropBlock, 2));
        }
        else {
            drops.add(new ItemStack(dropBlock, 1));
        }
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
