package com.momo.fd.blocks;

import com.momo.fd.MoMoFramework;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material, MapColor mapColor, int burnTime)
	{
		super(material, mapColor);
		setUnlocalizedName(name);
		setRegistryName(name);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this){
			@Override
			public int getItemBurnTime(ItemStack itemStack)
			{
				return burnTime;
			}
		}.setRegistryName(this.getRegistryName()));

	}

	public BlockBase(String name, Material material, int burnTime){
		this(name, material, material.getMaterialMapColor(), burnTime);
	}

	public BlockBase(String name, Material material, MapColor mapColor){
		this(name, material, mapColor, -1);
	}

	public BlockBase(String name, Material material){
		this(name, material, material.getMaterialMapColor());
	}

	@Override
	public void registerModels() {
		MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
