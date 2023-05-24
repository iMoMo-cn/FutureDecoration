package com.momo.fd.blocks.blockBasic.ore;

import com.momo.fd.blocks.blockVariant.baseVariant.BlockVariantBase;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import java.util.Random;

public class Ore extends BlockVariantBase {
    public static final SoundType DEEPSLATE = new SoundType(1.0F, 1.0F, ModSoundHandler.BLOCK_DEEPSLATE_BREAK, ModSoundHandler.BLOCK_DEEPSLATE_STEP, ModSoundHandler.BLOCK_DEEPSLATE_PLACE, ModSoundHandler.BLOCK_DEEPSLATE_HIT, ModSoundHandler.BLOCK_DEEPSLATE_FALL);

    public Ore(int maxUsedVariants) {
        super("ore", Material.ROCK, MapColor.GRAY, maxUsedVariants);
        setHardness(3.0F);
        setResistance(5.0F);

        setSoundType(DEEPSLATE);
    }

    /**
     * Gets the metadata of the item this BlockPhasingOre can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        int data = state.getValue(VARIANT).getMeta();

        if(data <= 2)
        {
            return data;
        }
        if(data == 4)
        {
            return 4;
        }
        else {
            return 0;
        }
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        int data = state.getValue(VARIANT).getMeta();

        if(data <= 2)
        {
            return ModItems.RAW_ORE;
        }
        if(data == 3)
        {
            return Items.COAL;
        }
        if(data == 4)
        {
            return Items.DYE;
        }
        if(data == 5)
        {
            return Items.DIAMOND;
        }
        if(data == 6)
        {
            return Items.EMERALD;
        }
        else {
            return Item.getItemFromBlock(this);
        }
    }

    /**
     * State and fortune sensitive version, this replaces the old (int meta, Random rand)
     * version in 1.1.
     *
     * @param state Current state
     * @param fortune Current item fortune level
     * @param random Random number generator
     * @return The number of items to drop
     */
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        int data = state.getValue(VARIANT).getMeta();

        return quantityDroppedWithBonus(data, fortune, random);
    }


    public int quantityDropped(int data, Random random)
    {
        int i = 1;

        if(data == 4)
        {
            i = 4 + random.nextInt(5);
        }

        return i;
    }

    public int quantityDroppedWithBonus(int data, int fortune, Random random)
    {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(data, random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(data, random);
        }
    }

    @Override
    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        Random rand = world instanceof World ? ((World)world).rand : new Random();

        int data = state.getValue(VARIANT).getMeta();

        if (this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this))
        {
            int i = 0;
            if (data == 0)
            {
                i = MathHelper.getInt(rand, 1, 3);
            }
            else if (data == 1)
            {
                i = MathHelper.getInt(rand, 1, 3);
            }
            else if (data == 2)
            {
                i = MathHelper.getInt(rand, 2, 4);
            }
            else if (data == 3)
            {
                i = MathHelper.getInt(rand, 0, 2);
            }
            else if (data == 4)
            {
                i = MathHelper.getInt(rand, 2, 5);
            }
            else if (data == 5)
            {
                i = MathHelper.getInt(rand, 3, 7);
            }
            else if (data == 6)
            {
                i = MathHelper.getInt(rand, 3, 7);
            }

            return i;
        }
        return 0;
    }
}
