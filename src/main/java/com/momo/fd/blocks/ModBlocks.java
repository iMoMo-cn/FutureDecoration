package com.momo.fd.blocks;

import com.momo.fd.blocks.blockBasic.*;
import com.momo.fd.blocks.blockInteractive.Button;
import com.momo.fd.blocks.blockInteractive.TrapDoor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static final Block CHARCOAL_BLOCK = new BurnableBlock("charcoal_block", 16000, Material.ROCK, MapColor.BLACK).setHardness(5.0F).setResistance(10.0F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	public static final Block SMOOTH_STONE = new BlockBase("smooth_stone", Material.ROCK).setHardness(1.5F).setResistance(10.0F).setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

	public static final Block ACACIA_TRAPDOOR = new TrapDoor("acacia_trapdoor", Material.WOOD);
	public static final Block BIRCH_TRAPDOOR = new TrapDoor("birch_trapdoor", Material.WOOD);
	public static final Block DARK_OAK_TRAPDOOR = new TrapDoor("dark_oak_trapdoor", Material.WOOD);
	public static final Block JUNGLE_TRAPDOOR = new TrapDoor("jungle_trapdoor", Material.WOOD);
	public static final Block SPRUCE_TRAPDOOR = new TrapDoor("spruce_trapdoor", Material.WOOD);

	public static final Block ACACIA_BUTTON = new Button("acacia_button", true);
	public static final Block BIRCH_BUTTON = new Button("birch_button", true);
	public static final Block DRAK_OAK_BUTTON = new Button("dark_oak_button", true);
	public static final Block JUNGLE_BUTTON = new Button("jungle_button", true);
	public static final Block SPRUCE_BUTTON = new Button("spruce_button", true);

	public static final Block STONE_WALL = new Wall("stone_wall", Blocks.STONE);
	public static final Block STONE_BRICK_WALL = new Wall("stone_brick_wall", Blocks.BRICK_BLOCK);
	public static final Block MOSSY_STONE_BRICK_WALL = new Wall("mossy_stone_brick_wall", Blocks.BRICK_BLOCK);
	public static final Block SAND_STONE_WALL = new Wall("sand_stone_wall", Blocks.BRICK_BLOCK);
	public static final Block RED_SAND_STONE_WALL = new Wall("red_sand_stone_wall", Blocks.BRICK_BLOCK);
	public static final Block BIRCK_WALL = new Wall("brick_wall", Blocks.BRICK_BLOCK);

	public static final Block ANDESITE_WALL = new Wall("andesite_wall", Blocks.STONEBRICK);
	public static final Block SMOOTH_ANDESITE_WALL = new Wall("andesite_wall_smooth", Blocks.STONEBRICK);
	public static final Block DIORITE_WALL = new Wall("diorite_wall", Blocks.STONEBRICK);
	public static final Block SMOOTH_DIORITE_WALL = new Wall("diorite_wall_smooth", Blocks.STONEBRICK);
	public static final Block GRANITE_WALL = new Wall("granite_wall", Blocks.STONEBRICK);
	public static final Block SMOOTH_GRANITE_WALL = new Wall("granite_wall_smooth", Blocks.STONEBRICK);

	public static final Block SMOOTH_STONE_WALL = new Wall("smooth_stone_wall", Blocks.STONE_SLAB);
	public static final Block NETHER_BRICK_WALL = new Wall("nether_brick_wall", Blocks.NETHER_BRICK);
	public static final Block RED_NETHER_BRICK_FENCE = new Fence("red_nether_brick_fence", Material.ROCK, MapColor.NETHERRACK);
    public static final Block RED_NETHER_BRICK_WALL = new Wall("red_nether_brick_wall", Blocks.RED_NETHER_BRICK);
	public static final Block END_STONE_BRICK_WALL = new Wall("end_stone_brick_wall", Blocks.END_BRICKS);
	public static final Block PURPUR_WALL = new Wall("purpur_wall", Blocks.PURPUR_BLOCK);
	public static final Block PRISMARINE_WALL = new Wall("prismarine_wall", Blocks.PRISMARINE);
	public static final Block PRISMARINE_DRAK_WALL = new Wall("prismarine_dark_wall", Blocks.PRISMARINE);
	public static final Block PRISMARINE_BRICK_WALL = new Wall("prismarine_brick_wall", Blocks.PRISMARINE);

	public static final BlockSlab STONE_SLAB = new Slab("stone_slab", MapColor.STONE);
	public static final BlockSlab MOSSY_STONE_SLAB = new Slab("mossy_stone_slab", MapColor.STONE);
	public static final BlockSlab MOSSY_STONE_BRICK_SLAB = new Slab("mossy_stone_brick_slab", MapColor.STONE);

	public static final BlockSlab ANDESITE_SLAB = new Slab("andesite_slab", MapColor.STONE);
	public static final BlockSlab SMOOTH_ANDESITE_SLAB = new Slab("andesite_slab_smooth", MapColor.STONE);
	public static final BlockSlab DIORITE_SLAB = new Slab("diorite_slab", MapColor.QUARTZ);
	public static final BlockSlab SMOOTH_DIORITE_SLAB = new Slab("diorite_slab_smooth", MapColor.QUARTZ);
	public static final BlockSlab GRANITE_SLAB = new Slab("granite_slab", MapColor.DIRT);
	public static final BlockSlab SMOOTH_GRANITE_SLAB = new Slab("granite_slab_smooth", MapColor.DIRT);

	public static final BlockSlab RED_NETHER_BRICK_SLAB = new Slab("red_nether_brick_slab", MapColor.NETHERRACK);
	public static final BlockSlab END_STONE_BRICK_SLAB = new Slab("end_stone_brick_slab", MapColor.SAND);

	public static final BlockSlab PRISMARINE_SLAB = new Slab("prismarine_slab", MapColor.CYAN);
	public static final BlockSlab PRISMARINE_DRAK_SLAB = new Slab("prismarine_dark_slab", MapColor.DIAMOND);
	public static final BlockSlab PRISMARINE_BRICK_SLAB = new Slab("prismarine_brick_slab", MapColor.DIAMOND);

	public static final Block STONE_STAIRS = new Stair("stone_stairs", Blocks.STONE);
	public static final Block MOSSY_STONE_STAIRS = new Stair("mossy_stone_stairs", Blocks.MOSSY_COBBLESTONE);
	public static final Block MOSSY_STONE_BRICK_STAIRS = new Stair("mossy_stone_brick_stairs", Blocks.MOSSY_COBBLESTONE);
	public static final Block SMOOTH_STONE_STAIRS = new Stair("smooth_stone_stairs", Blocks.STONE);

	public static final Block ANDESITE_STAIRS = new Stair("andesite_stairs", Blocks.STONEBRICK);
	public static final Block SMOOTH_ANDESITE_STAIRS = new Stair("smooth_andesite_stairs", Blocks.STONEBRICK);
	public static final Block DIORITE_STAIRS = new Stair("diorite_stairs", Blocks.QUARTZ_BLOCK);
	public static final Block SMOOTH_DIORITE_STAIRS = new Stair("smooth_diorite_stairs", Blocks.QUARTZ_BLOCK);
	public static final Block GRANITE_STAIRS = new Stair("granite_stairs", Blocks.RED_NETHER_BRICK);
	public static final Block SMOOTH_GRANITE_STAIRS = new Stair("smooth_granite_stairs", Blocks.RED_NETHER_BRICK);

	public static final Block RED_NETHER_BRICK_STAIRS = new Stair("red_nether_brick_stairs", Blocks.RED_NETHER_BRICK);
	public static final Block END_STONE_BRICK_STAIRS = new Stair("end_stone_brick_stairs", Blocks.END_BRICKS);

	public static final Block PRISMARINE_STAIRS = new Stair("prismarine_stairs", Blocks.PRISMARINE);
	public static final Block PRISMARINE_DARK_STAIRS = new Stair("prismarine_dark_stairs", Blocks.PRISMARINE);
	public static final Block PRISMARINE_BRICK_STAIRS = new Stair("prismarine_brick_stairs", Blocks.PRISMARINE);

    //TODO add Lamp and Chain

    //TODO add Colorful Flower Pot

    //TODO add Colorful GrowStone

    //TODO add different Wooden Chests and Barrels

}
