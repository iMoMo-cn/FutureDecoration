package com.momo.fd.blocks.blockBasic.leaves;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class AzaleaLeaves extends BlockLeaves implements IHasModel {

    public static final SoundType AZALEA_LEAVES = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_AZALEA_LEAVES_BREAK, ModSoundHandler.BLOCK_AZALEA_LEAVES_STEP, ModSoundHandler.BLOCK_AZALEA_PLACE, ModSoundHandler.BLOCK_AZALEA_LEAVES_HIT, ModSoundHandler.BLOCK_AZALEA_LEAVES_FALL);

    public AzaleaLeaves()
    {
        super();

        setUnlocalizedName("azalea_leaves");
        setRegistryName("azalea_leaves");

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        setSoundType(AZALEA_LEAVES);

        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }

    @Override
    public void registerModels() { MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory"); }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(ModBlocks.AZALEA);
    }

    public int damageDropped(IBlockState state) { return 0; }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() { return BlockRenderLayer.CUTOUT_MIPPED; }

    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) { return true; }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {DECAYABLE, CHECK_DECAY});
    }

    public IBlockState getStateFromMeta(int meta)
    {
        if(meta == 0) return this.getDefaultState().withProperty(DECAYABLE, false).withProperty(CHECK_DECAY, false);
        if(meta == 1) return this.getDefaultState().withProperty(DECAYABLE, false).withProperty(CHECK_DECAY, true);
        if(meta == 2) return this.getDefaultState().withProperty(DECAYABLE, true).withProperty(CHECK_DECAY, false);
        return this.getDefaultState().withProperty(DECAYABLE, true).withProperty(CHECK_DECAY, true);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i;

        if(state.getValue(DECAYABLE))
            i = state.getValue(CHECK_DECAY)? 3 : 2;
        else
            i = state.getValue(CHECK_DECAY)? 1 : 0;

        return i;
    }

    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && stack.getItem() == Items.SHEARS)
        {
            player.addStat(StatList.getBlockStats(this));
            spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this)));
        }
        else
        {
            super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.OAK;
    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(this));
    }
}
