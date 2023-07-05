package com.momo.fd.blocks.blockFood;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCake extends net.minecraft.block.BlockCake implements IHasModel
{

    public BlockCake(String name)
    {
        super();

        setUnlocalizedName(name);
        setRegistryName(name);

        setHardness(0.5F);
        setSoundType(SoundType.CLOTH);

        disableStats();

        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public void registerModels() {

    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(ModItems.COCOA_CAKE);
    }
}
