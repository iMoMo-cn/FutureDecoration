package com.momo.fd.blocks.blockInteractive;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Door extends BlockDoor implements IHasModel {

    private Item itemDoor;

    public Door(String name)
    {
        super(Material.IRON);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        ModBlocks.BLOCKS.add(this);
    }

    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.getBlock() == ModBlocks.GOLD_DOOR ? MapColor.GOLD : super.getMapColor(state, worldIn, pos);
    }

    public Door setItemDoor(Item itemDoorIn)
    {
        this.itemDoor = itemDoorIn;
        return this;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(itemDoor);
    }

    @Override
    public void registerModels() { }
}
