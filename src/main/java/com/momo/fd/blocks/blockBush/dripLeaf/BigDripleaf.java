package com.momo.fd.blocks.blockBush.dripLeaf;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BigDripleaf extends BlockBush implements IHasModel, IGrowable {

    public static final SoundType DRIPLEAF = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_BIG_DRIPLEAF_BREAK, ModSoundHandler.BLOCK_BIG_DRIPLEAF_STEP, ModSoundHandler.BLOCK_BIG_DRIPLEAF_PLACE, ModSoundHandler.BLOCK_BIG_DRIPLEAF_HIT, ModSoundHandler.BLOCK_BIG_DRIPLEAF_FALL);

    protected static final AxisAlignedBB HALF_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    protected static final AxisAlignedBB DRIP_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

    public static final PropertyEnum<EnumFacing> FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<BigDripleaf.EnumTilt> TILT = PropertyEnum.<BigDripleaf.EnumTilt>create("tilt", BigDripleaf.EnumTilt.class);

    public int tiltLevel;
    public boolean playSound;

    public BigDripleaf() {
        super(Material.VINE);

        setHardness(0.0F);

        setUnlocalizedName("big_dripleaf");
        setRegistryName("big_dripleaf");

        setHardness(0.1F);
        setResistance(0.1F);
        setHarvestLevel("axe", 0);

        setSoundType(BigDripleaf.DRIPLEAF);

        this.tiltLevel = 0;
        this.playSound = false;
        this.setTickRandomly(true);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));

        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH).withProperty(TILT, EnumTilt.NONE));
    }

    @Override
    public void registerModels() { MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory"); }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { return FULL_BLOCK_AABB; }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        EnumTilt tilt = blockState.getValue(TILT);

        if(tilt == EnumTilt.FULL)
            return NULL_AABB;
        else if(tilt == EnumTilt.PARTIAL)
            return HALF_AABB;

        return DRIP_AABB;
    }

    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
    {

    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if(playSound)
        {
            worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), ModSoundHandler.BLOCK_BIG_DRIPLEAF_BREAK, SoundCategory.BLOCKS, 0.8F, 0.8F, true);
            playSound = false;
        }
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if(tiltLevel == 3)
        {
            worldIn.setBlockState(pos, state.withProperty(TILT, EnumTilt.NONE));
        }

        if(tiltLevel == 1)
        {
            worldIn.setBlockState(pos, state.withProperty(TILT, EnumTilt.PARTIAL));
            playSound = true;
            checkReset(worldIn, pos);
        }

        if(tiltLevel == 2)
        {
            worldIn.setBlockState(pos, state.withProperty(TILT, EnumTilt.FULL));
            playSound = true;
            tiltLevel = 3;
            worldIn.scheduleUpdate(pos, this, 100);
        }
    }

    public void checkReset(World worldIn, BlockPos pos)
    {
        AxisAlignedBB AABB = new AxisAlignedBB((double)pos.getX(), (double)(pos.getY() + 1), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1));

        if (!worldIn.isRemote)
        {
            if(worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null ,AABB).isEmpty() && worldIn.getBlockState(pos).getValue(TILT) == EnumTilt.PARTIAL)
            {
                tiltLevel = 3;
                worldIn.scheduleUpdate(pos, this, 100);
            }
        }
    }

    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        EnumTilt tilt = state.getValue(TILT);

        AxisAlignedBB AABB = new AxisAlignedBB((double)pos.getX(), (double)(pos.getY() + 1), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1));

        if (!worldIn.isRemote)
        {
            if(!worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null ,AABB).isEmpty() && tilt != EnumTilt.UNSTABLE)
            {
                if(tilt == EnumTilt.NONE)
                {
                    tiltLevel = 1;
                    worldIn.scheduleUpdate(pos, this, 20);
                }

                if(tilt == EnumTilt.PARTIAL)
                {
                    tiltLevel = 2;
                    worldIn.scheduleUpdate(pos, this, 20);
                }
            }
        }

    }


    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
            boolean flag1 = worldIn.isBlockPowered(pos);
            boolean flag2 = blockIn.getDefaultState().canProvidePower();
            boolean flag3 = state.getValue(TILT) == EnumTilt.UNSTABLE;

            if (flag1 || flag2)
            {
                if (!flag3)
                {
                    worldIn.setBlockState(pos, state.withProperty(TILT, EnumTilt.UNSTABLE), 2);
                }
                if(!flag1)
                {
                    worldIn.setBlockState(pos, state.withProperty(TILT, EnumTilt.NONE), 2);
                }
            }
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
    }

    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());

        return soil.getBlock() == ModBlocks.DRIPLEAF_STEM || super.canBlockStay(worldIn, pos, state);
    }

    public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) { return false; }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState getStateFromMeta(int meta)
    {
        int i = meta / 4;
        IBlockState state = this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(i));

        int j = meta % 4;

        return state.withProperty(TILT, EnumTilt.byMetadata(j));
    }

    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(TILT).getMetadata() + state.getValue(FACING).getHorizontalIndex() * 4;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, TILT});
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) { return true; }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) { return true; }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        EnumFacing facing = state.getValue(FACING);

        if(worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR)
        {
            worldIn.setBlockState(pos, ModBlocks.DRIPLEAF_STEM.getDefaultState().withProperty(FACING, facing), 2);
            worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(FACING, facing), 3);
        }
    }

    public static enum EnumTilt implements IStringSerializable
    {
        NONE(0, "none"),
        PARTIAL(1, "partial"),
        FULL(2, "full"),
        UNSTABLE(3, "unstable");

        private static final BigDripleaf.EnumTilt[] META_LOOKUP = new BigDripleaf.EnumTilt[values().length];
        private final int meta;
        private final String name;

        private EnumTilt(int metaIn, String nameIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BigDripleaf.EnumTilt byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        static
        {
            for (BigDripleaf.EnumTilt bigdripleaf$enumtilt : values())
            {
                META_LOOKUP[bigdripleaf$enumtilt.getMetadata()] = bigdripleaf$enumtilt;
            }
        }
    }

}
