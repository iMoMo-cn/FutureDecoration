package com.momo.fd.blocks.blockMisc.carpet;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.blocks.blockVariant.baseVariant.EnumVariants;
import com.momo.fd.blocks.blockVariant.baseVariant.IMetaName;
import com.momo.fd.item.ModItems;
import com.momo.fd.item.itemVariant.ItemBurnableBlockVariants;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TightCarpet extends Block implements IMetaName, IHasModel {
   protected static final AxisAlignedBB CARPET_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
    public static final PropertyEnum<EnumVariants> VARIANT = PropertyEnum.create("variant", EnumVariants.class);
    final int maxVariants;

    public TightCarpet(String name, int maxUsedVariants) {
        super(Material.CARPET, MapColor.CLOTH);

        setUnlocalizedName(name);
        setRegistryName(name);

        setHardness(0.1F);
        setTickRandomly(true);

        setCreativeTab(CreativeTabs.DECORATIONS);
        setSoundType(SoundType.CLOTH);

        setLightOpacity(0);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBurnableBlockVariants(this, maxUsedVariants, 67).setRegistryName(this.getRegistryName()));

        if (maxUsedVariants < 0 || maxUsedVariants > 16)
        {
            MoMoFramework.Log("Illegal variants for %s:%d", getUnlocalizedName() ,maxUsedVariants);
            maxUsedVariants = 1;
        }

        this.maxVariants = maxUsedVariants;
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumVariants.Block0));
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return CARPET_AABB;
    }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    /**
     * Checks if this block can be placed exactly at the given position.
     */
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos);
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if (side == EnumFacing.UP)
        {
            return true;
        }
        else
        {
            return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? true : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
        }
    }

    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     *
     * @return an approximation of the form of the given face
     */
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
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

    @Override
    public void registerModels() {
//		for (int i = 0; i < EnumVariants.values().length; i++) {
//			MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), i, this.unlocalizedName + "_" + EnumVariants.values()[i].getName(),
//					IDLNBTDef.NAME_INVENTORY);
//		}
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
