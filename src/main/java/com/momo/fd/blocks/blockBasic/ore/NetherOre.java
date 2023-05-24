package com.momo.fd.blocks.blockBasic.ore;

import com.momo.fd.blocks.blockVariant.BlockVariantBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class NetherOre extends BlockVariantBase {

    public NetherOre(int maxUsedVariants) {
        super("nether_ore", Material.ROCK, MapColor.NETHERRACK, maxUsedVariants);
        setHardness(3.0F);
        setResistance(3.0F);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        int data = state.getValue(VARIANT).getMeta();

        if (data == 0)
        {
            return Items.GOLD_NUGGET;
        }
        else if (data == 1)
        {
            return Items.IRON_NUGGET;
        }
        else
        {
            return Item.getItemFromBlock(this);
        }
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

            if (data == 0 || data == 1)
            {
                i = MathHelper.getInt(rand, 0, 1);
            }

            return i;
        }
        return 0;
    }

    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        int data = state.getValue(VARIANT).getMeta();

        return quantityDroppedWithBonus(data, fortune, random);
    }

    public int quantityDropped(int data, Random random)
    {
        int i = 1;

        if(data == 0 || data == 1)
        {
            i = 2 + random.nextInt(5);
        }

        return i;
    }

    /**
     * Gets the metadata of the item this BlockPhasingOre can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

}
