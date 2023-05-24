package com.momo.fd.blocks.blockBush;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BerriesBush extends BlockCrops implements IHasModel{
    public static final PropertyInteger BERRIES_AGE = PropertyInteger.create("age", 0, 3);
    private static final AxisAlignedBB[] BERRIES_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.81125D, 0.5D, 0.81125D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D), new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    public static final SoundType BERRY_BUSH = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_BERRY_BREAK, SoundEvents.BLOCK_GLASS_STEP, ModSoundHandler.BLOCK_BERRY_PLACE, SoundEvents.BLOCK_GRASS_HIT, SoundEvents.BLOCK_GRASS_FALL);

    public BerriesBush(String name)
    {
        setHardness(0.0F);
        setLightOpacity(0);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        this.setSoundType(BERRY_BUSH);

        ModBlocks.BLOCKS.add(this);

        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), Integer.valueOf(0)));

    }

    @Override
    public void registerModels() {

    }

    protected boolean canSustainBush(IBlockState state)
    {
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND;
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModItems.SWEET_BERRIES);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int age = getAge(state);
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        if (age >= 2)
        {
            drops.add(new ItemStack(this.getSeed(), rand.nextInt(2) + age - 1, 0));
        }
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        int age = getAge(state);
        Random rand = new Random();
        boolean flag1 = playerIn.getHeldItemOffhand().getItem() == (new ItemStack(Items.DYE, 1, 0)).getItem();
        boolean flag2 = playerIn.getHeldItemMainhand().getItem() == (new ItemStack(Items.DYE, 1, 0)).getItem();
        boolean flag3 = playerIn.getHeldItemOffhand().getItem() instanceof ItemBlock;
        boolean flag4 = playerIn.getHeldItemOffhand().getItem() instanceof ItemBlock;

        boolean flag = flag1 || flag2 || flag3 || flag4;

        if(age > 2)
        {
            flag = false;
        }

        if(!flag && age >= 2)
        {
            if (!worldIn.isRemote)
            {
                worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.SWEET_BERRIES, rand.nextInt(2) + age - 1)));
                worldIn.setBlockState(pos, ModBlocks.BERRY_BUSH.getDefaultState().withProperty(BERRIES_AGE, 1));
            }

            if (worldIn.isRemote)
            {
                worldIn.playSound(playerIn, pos, ModSoundHandler.PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            }
        }
        return false;
    }

    protected PropertyInteger getAgeProperty()
    {
        return BERRIES_AGE;
    }

    public PropertyInteger getProperty()
    {
        return BERRIES_AGE;
    }

    public int getMaxAge()
    {
        return 3;
    }

    protected Item getSeed()
    {
        return ModItems.SWEET_BERRIES;
    }

    protected Item getCrop()
    {
        return ModItems.SWEET_BERRIES;
    }


    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (rand.nextInt(3) == 0)
        {
            this.checkAndDropBlock(worldIn, pos, state);
        }
        else
        {
            super.updateTick(worldIn, pos, state, rand);
        }
    }

    protected int getBonemealAgeIncrease(World worldIn)
    {
        return super.getBonemealAgeIncrease(worldIn) / 3;
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {BERRIES_AGE});
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BERRIES_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return getAge(state) < 3;
    }

    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if(worldIn.getBlockState(pos).getBlock() instanceof BerriesBush)
        {
            if(entityIn instanceof EntityLivingBase && worldIn.isRemote)
            {
                EntityLivingBase entity = (EntityLivingBase)entityIn;

                entity.motionX *= 0.34D;
                entity.motionY *= 0.34D;
                entity.motionZ *= 0.34D;

                entity.attackEntityFrom(DamageSource.CACTUS, 1.0F);
            }
        }
    }
}
