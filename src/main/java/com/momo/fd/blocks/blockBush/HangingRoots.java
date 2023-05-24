package com.momo.fd.blocks.blockBush;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class HangingRoots extends BlockBush implements IHasModel , IShearable {

    public static final SoundType HANGING_ROOTS = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_HANGING_ROOTS_BREAK, ModSoundHandler.BLOCK_HANGING_ROOTS_STEP, ModSoundHandler.BLOCK_HANGING_ROOTS_PLACE, ModSoundHandler.BLOCK_HANGING_ROOTS_HIT, ModSoundHandler.BLOCK_HANGING_ROOTS_FALL);

    protected static final AxisAlignedBB ROOTS_AABB = new AxisAlignedBB(0.0D, 0.4375D, 0.0D, 1.0D, 1.0D, 1.0D);

    public HangingRoots()
    {
        super(Material.PLANTS, MapColor.DIRT);
        setHardness(0.0F);
        setLightOpacity(0);

        setHarvestLevel("spade", 0);

        setRegistryName("hanging_roots");
        setUnlocalizedName("hanging_roots");

        setSoundType(HANGING_ROOTS);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return ROOTS_AABB;
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState top = worldIn.getBlockState(pos.up());

        return top.isSideSolid(worldIn, pos.up(), EnumFacing.DOWN);
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canPlaceBlockAt(worldIn, pos))
        {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune) { return Items.AIR; }


    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) { return true; }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this));
    }
}
