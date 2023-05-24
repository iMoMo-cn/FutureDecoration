package com.momo.fd.blocks.blockVariant;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockVariant.baseVariant.EnumVariants;
import com.momo.fd.blocks.blockVariant.baseVariant.IMetaName;
import com.momo.fd.item.itemVariant.ItemBlockVariants;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class StainedGlass extends BlockGlass implements IHasModel, IMetaName
{
    public static final PropertyEnum<EnumVariants> VARIANT = PropertyEnum.create("variant", EnumVariants.class);

    public final int maxVariants;

    public StainedGlass(String name, int maxUsedVariants) {
        super(Material.GRASS, false);

        setUnlocalizedName(name);
        setRegistryName(name);

        setLightOpacity(0);
        setHardness(0.3F);
        setSoundType(SoundType.GLASS);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlockVariants(this, maxUsedVariants).setRegistryName(this.getRegistryName()));

        if (maxUsedVariants < 0 || maxUsedVariants > 16)
        {
            MoMoFramework.Log("Illegal variants for %s:%d", getUnlocalizedName() ,maxUsedVariants);
            maxUsedVariants = 1;
        }
        this.maxVariants = maxUsedVariants;
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariants.Block0));
    }

    @Override
    public void registerModels() {
        //MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.TRANSLUCENT; }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

        if (blockState != iblockstate)
        {
            return true;
        }

        if (block == this)
        {
            return false;
        }

        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

    /**
     * Gets the metadata of the item this BlockPhasingOre can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return state.getValue(VARIANT).getMeta();
    }

    /**
     * Convert the given metadata into a BlockState for this BlockPhasingOre
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, EnumVariants.byMetadata(meta));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(VARIANT).getMeta();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        int i = 0;
        for (EnumVariants variant : EnumVariants.values())
        {
            if (i >= maxVariants) { break; }
            i++;
            items.add(new ItemStack(this, 1, variant.getMeta()));
        }
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    @Override
    public String getSpecialName(ItemStack stack) {
        return EnumVariants.values()[stack.getItemDamage()].getName();
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(VARIANT).getMeta() == 0)  return MapColor.BLACK;
        if (state.getValue(VARIANT).getMeta() == 1)  return MapColor.BLUE;
        if (state.getValue(VARIANT).getMeta() == 2)  return MapColor.BROWN;
        if (state.getValue(VARIANT).getMeta() == 3)  return MapColor.CYAN;
        if (state.getValue(VARIANT).getMeta() == 4)  return MapColor.GRAY;
        if (state.getValue(VARIANT).getMeta() == 5)  return MapColor.GREEN;
        if (state.getValue(VARIANT).getMeta() == 6)  return MapColor.LIGHT_BLUE;
        if (state.getValue(VARIANT).getMeta() == 7)  return MapColor.LIME;
        if (state.getValue(VARIANT).getMeta() == 8)  return MapColor.MAGENTA;
        if (state.getValue(VARIANT).getMeta() == 9)  return MapColor.ADOBE;
        if (state.getValue(VARIANT).getMeta() == 10)  return MapColor.PINK;
        if (state.getValue(VARIANT).getMeta() == 11)  return MapColor.PURPLE;
        if (state.getValue(VARIANT).getMeta() == 12)  return MapColor.RED;
        if (state.getValue(VARIANT).getMeta() == 13)  return MapColor.SILVER;
        if (state.getValue(VARIANT).getMeta() == 14)  return MapColor.SNOW;
        return MapColor.YELLOW;
    }
}
