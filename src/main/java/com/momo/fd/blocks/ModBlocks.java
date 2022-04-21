package com.momo.fd.blocks;

import com.momo.fd.blocks.blockBasic.*;
import com.momo.fd.blocks.blockBasic.sign.StandingSign;
import com.momo.fd.blocks.blockBasic.sign.WallSign;
import com.momo.fd.blocks.blockInteractive.Button;
import com.momo.fd.blocks.blockInteractive.PressurePlate;
import com.momo.fd.blocks.blockInteractive.TrapDoor;
import com.momo.fd.blocks.blockMisc.NewObsidian;
import com.momo.fd.item.ModItems;
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
	public static final Block CRYING_OBSIDIAN = new NewObsidian("crying_obsidian", Material.ROCK, MapColor.OBSIDIAN).setLightLevel(0.666666667F);

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

	public static final BlockSlab STONE_SLAB_DOUBLE = new Slab("stone_slab_double", MapColor.STONE).setDropped(ModBlocks.STONE_SLAB);
	public static final BlockSlab STONE_SLAB = new Slab("stone_slab", MapColor.STONE, ModBlocks.STONE_SLAB_DOUBLE);
	public static final BlockSlab MOSSY_STONE_SLAB_DOUBLE = new Slab("mossy_stone_slab_double", MapColor.STONE).setDropped(ModBlocks.MOSSY_STONE_SLAB);
	public static final BlockSlab MOSSY_STONE_SLAB = new Slab("mossy_stone_slab", MapColor.STONE, ModBlocks.MOSSY_STONE_SLAB_DOUBLE);
	public static final BlockSlab MOSSY_STONE_BRICK_SLAB_DOUBLE = new Slab("mossy_stone_brick_slab_double", MapColor.STONE).setDropped(ModBlocks.MOSSY_STONE_BRICK_SLAB);
	public static final BlockSlab MOSSY_STONE_BRICK_SLAB = new Slab("mossy_stone_brick_slab", MapColor.STONE, ModBlocks.MOSSY_STONE_BRICK_SLAB_DOUBLE);

	public static final BlockSlab ANDESITE_SLAB_DOUBLE = new Slab("andesite_slab_double", MapColor.STONE).setDropped(ModBlocks.ANDESITE_SLAB);
	public static final BlockSlab ANDESITE_SLAB = new Slab("andesite_slab", MapColor.STONE, ModBlocks.ANDESITE_SLAB_DOUBLE);
	public static final BlockSlab SMOOTH_ANDESITE_SLAB_DOUBLE = new Slab("andesite_slab_smooth_double", MapColor.STONE).setDropped(ModBlocks.SMOOTH_ANDESITE_SLAB);
	public static final BlockSlab SMOOTH_ANDESITE_SLAB = new Slab("andesite_slab_smooth", MapColor.STONE, ModBlocks.SMOOTH_ANDESITE_SLAB_DOUBLE);
	public static final BlockSlab DIORITE_SLAB_DOUBLE = new Slab("diorite_slab_double", MapColor.QUARTZ).setDropped(ModBlocks.DIORITE_SLAB);
	public static final BlockSlab DIORITE_SLAB = new Slab("diorite_slab", MapColor.QUARTZ, ModBlocks.DIORITE_SLAB_DOUBLE);
	public static final BlockSlab SMOOTH_DIORITE_SLAB_DOUBLE = new Slab("diorite_slab_smooth_double", MapColor.QUARTZ).setDropped(ModBlocks.SMOOTH_DIORITE_SLAB);
	public static final BlockSlab SMOOTH_DIORITE_SLAB = new Slab("diorite_slab_smooth", MapColor.QUARTZ, ModBlocks.SMOOTH_DIORITE_SLAB_DOUBLE);
	public static final BlockSlab GRANITE_SLAB_DOUBLE = new Slab("granite_slab_double", MapColor.DIRT).setDropped(ModBlocks.GRANITE_SLAB);
	public static final BlockSlab GRANITE_SLAB = new Slab("granite_slab", MapColor.DIRT, ModBlocks.GRANITE_SLAB_DOUBLE);
	public static final BlockSlab SMOOTH_GRANITE_SLAB_DOUBLE = new Slab("granite_slab_smooth_double", MapColor.DIRT).setDropped(ModBlocks.SMOOTH_GRANITE_SLAB);
	public static final BlockSlab SMOOTH_GRANITE_SLAB = new Slab("granite_slab_smooth", MapColor.DIRT, ModBlocks.SMOOTH_GRANITE_SLAB_DOUBLE);

	public static final BlockSlab RED_NETHER_BRICK_SLAB_DOUBLE = new Slab("red_nether_brick_slab_double", MapColor.NETHERRACK).setDropped(ModBlocks.RED_NETHER_BRICK_SLAB);
	public static final BlockSlab RED_NETHER_BRICK_SLAB = new Slab("red_nether_brick_slab", MapColor.NETHERRACK, ModBlocks.RED_NETHER_BRICK_SLAB_DOUBLE);
	public static final BlockSlab END_STONE_BRICK_SLAB_DOUBLE = new Slab("end_stone_brick_slab_double", MapColor.SAND).setDropped(ModBlocks.END_STONE_BRICK_SLAB);
	public static final BlockSlab END_STONE_BRICK_SLAB = new Slab("end_stone_brick_slab", MapColor.SAND, ModBlocks.END_STONE_BRICK_SLAB_DOUBLE);

	public static final BlockSlab PRISMARINE_SLAB_DOUBLE = new Slab("prismarine_slab_double", MapColor.CYAN).setDropped(ModBlocks.PRISMARINE_SLAB);
	public static final BlockSlab PRISMARINE_SLAB = new Slab("prismarine_slab", MapColor.CYAN, ModBlocks.PRISMARINE_SLAB_DOUBLE);
	public static final BlockSlab PRISMARINE_DRAK_SLAB_DOUBLE = new Slab("prismarine_dark_slab_double", MapColor.DIAMOND).setDropped(ModBlocks.PRISMARINE_DRAK_SLAB);
	public static final BlockSlab PRISMARINE_DRAK_SLAB = new Slab("prismarine_dark_slab", MapColor.DIAMOND, ModBlocks.PRISMARINE_DRAK_SLAB_DOUBLE);
	public static final BlockSlab PRISMARINE_BRICK_SLAB_DOUBLE = new Slab("prismarine_brick_slab_double", MapColor.DIAMOND).setDropped(ModBlocks.PRISMARINE_BRICK_SLAB);
	public static final BlockSlab PRISMARINE_BRICK_SLAB = new Slab("prismarine_brick_slab", MapColor.DIAMOND, ModBlocks.PRISMARINE_BRICK_SLAB_DOUBLE);

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

	public static final Block ACACIA_PRESSURE_PLATE = new PressurePlate("acacia_pressure_plate", Material.WOOD);
	public static final Block BIRCH_PRESSURE_PLATE = new PressurePlate("birch_pressure_plate", Material.WOOD);
	public static final Block DRAK_OAK_PRESSURE_PLATE = new PressurePlate("dark_oak_pressure_plate", Material.WOOD);
	public static final Block JUNGLE_PRESSURE_PLATE = new PressurePlate("jungle_pressure_plate", Material.WOOD);
	public static final Block SPRUCE_PRESSURE_PLATE = new PressurePlate("spruce_pressure_plate", Material.WOOD);

	public static final Block ACACIA_SIGN_STANDING = new StandingSign("acacia_sign_standing");
	public static final Block ACACIA_SIGN_WALL = new WallSign("acacia_sign_wall");
	public static final Block BIRCH_SIGN_STANDING = new StandingSign("birch_sign_standing");
	public static final Block BIRCH_SIGN_WALL = new WallSign("birch_sign_wall");
	public static final Block DARK_OAK_SIGN_STANDING = new StandingSign("dark_oak_sign_standing");
	public static final Block DARK_OAK_SIGN_WALL = new WallSign("dark_oak_sign_wall");
	public static final Block JUNGLE_SIGN_STANDING = new StandingSign("jungle_sign_standing");
	public static final Block JUNGLE_SIGN_WALL = new WallSign("jungle_sign_wall");
	public static final Block SPRUCE_SIGN_STANDING = new StandingSign("spruce_sign_standing");
	public static final Block SPRUCE_SIGN_WALL = new WallSign("spruce_sign_wall");

	public static final Block IRON_LANTERN = new Lantern("iron_lantern");
	public static final Block GOLD_LANTERN = new Lantern("gold_lantern");
	public static final Block GOLD_BARS = new Bar("gold_bars", Material.IRON);
	public static final Block GOLD_TRAPDOOR = new TrapDoor("gold_trapdoor", Material.IRON);

	public static final Block CLEAR_GLASS = new Glass("clear_glass");
	public static final Block CLEAR_GLASS_BLACK = new Glass("clear_glass_black");
	public static final Block CLEAR_GLASS_BLUE = new Glass("clear_glass_blue");
	public static final Block CLEAR_GLASS_BROWN = new Glass("clear_glass_brown");
	public static final Block CLEAR_GLASS_CYAN = new Glass("clear_glass_cyan");
	public static final Block CLEAR_GLASS_GRAY = new Glass("clear_glass_gray");
	public static final Block CLEAR_GLASS_GREEN = new Glass("clear_glass_green");
	public static final Block CLEAR_GLASS_LIGHT_BLUE = new Glass("clear_glass_light_blue");
	public static final Block CLEAR_GLASS_LIME = new Glass("clear_glass_lime");
	public static final Block CLEAR_GLASS_MAGENTA = new Glass("clear_glass_magenta");
	public static final Block CLEAR_GLASS_ORANGE = new Glass("clear_glass_orange");
	public static final Block CLEAR_GLASS_PINK = new Glass("clear_glass_pink");
	public static final Block CLEAR_GLASS_PURPLE = new Glass("clear_glass_purple");
	public static final Block CLEAR_GLASS_RED = new Glass("clear_glass_red");
	public static final Block CLEAR_GLASS_SILVER = new Glass("clear_glass_silver");
	public static final Block CLEAR_GLASS_WHITE = new Glass("clear_glass_white");
	public static final Block CLEAR_GLASS_YELLOW = new Glass("clear_glass_yellow");

    public static final Block CLEAR_PANE = new GlassPane("clear_pane");
    public static final Block CLEAR_PANE_BLACK = new GlassPane("clear_pane_black");
    public static final Block CLEAR_PANE_BLUE = new GlassPane("clear_pane_blue");
    public static final Block CLEAR_PANE_BROWN = new GlassPane("clear_pane_brown");
    public static final Block CLEAR_PANE_CYAN = new GlassPane("clear_pane_cyan");
    public static final Block CLEAR_PANE_GRAY = new GlassPane("clear_pane_gray");
    public static final Block CLEAR_PANE_GREEN = new GlassPane("clear_pane_green");
    public static final Block CLEAR_PANE_LIGHT_BLUE = new GlassPane("clear_pane_light_blue");
    public static final Block CLEAR_PANE_LIME = new GlassPane("clear_pane_lime");
    public static final Block CLEAR_PANE_MAGENTA = new GlassPane("clear_pane_magenta");
    public static final Block CLEAR_PANE_ORANGE = new GlassPane("clear_pane_orange");
    public static final Block CLEAR_PANE_PINK = new GlassPane("clear_pane_pink");
    public static final Block CLEAR_PANE_PURPLE = new GlassPane("clear_pane_purple");
    public static final Block CLEAR_PANE_RED = new GlassPane("clear_pane_red");
    public static final Block CLEAR_PANE_SILVER = new GlassPane("clear_pane_silver");
    public static final Block CLEAR_PANE_WHITE = new GlassPane("clear_pane_white");
    public static final Block CLEAR_PANE_YELLOW = new GlassPane("clear_pane_yellow");


	//TODO add Chain

    //TODO add Colorful Flower Pot

    //TODO add Colorful GlowStone

    //TODO add different Wooden Chests and Barrels

	public static void init(){
		((Slab)STONE_SLAB_DOUBLE).setDropped(ModBlocks.STONE_SLAB);
		((Slab)MOSSY_STONE_SLAB_DOUBLE).setDropped(ModBlocks.MOSSY_STONE_SLAB);
		((Slab)MOSSY_STONE_BRICK_SLAB_DOUBLE).setDropped(ModBlocks.MOSSY_STONE_BRICK_SLAB);
		((Slab)ANDESITE_SLAB_DOUBLE).setDropped(ModBlocks.ANDESITE_SLAB);
		((Slab)SMOOTH_ANDESITE_SLAB).setDropped(ModBlocks.SMOOTH_ANDESITE_SLAB);
		((Slab)DIORITE_SLAB_DOUBLE).setDropped(ModBlocks.DIORITE_SLAB);
		((Slab)SMOOTH_DIORITE_SLAB_DOUBLE).setDropped(ModBlocks.SMOOTH_DIORITE_SLAB);
		((Slab)GRANITE_SLAB_DOUBLE).setDropped(ModBlocks.GRANITE_SLAB);
		((Slab)SMOOTH_GRANITE_SLAB_DOUBLE).setDropped(ModBlocks.SMOOTH_GRANITE_SLAB);
		((Slab)RED_NETHER_BRICK_SLAB_DOUBLE).setDropped(ModBlocks.RED_NETHER_BRICK_SLAB);
		((Slab)END_STONE_BRICK_SLAB_DOUBLE).setDropped(ModBlocks.END_STONE_BRICK_SLAB);
		((Slab)PRISMARINE_SLAB_DOUBLE).setDropped(ModBlocks.PRISMARINE_SLAB);
		((Slab)PRISMARINE_DRAK_SLAB_DOUBLE).setDropped(ModBlocks.PRISMARINE_DRAK_SLAB);
		((Slab)PRISMARINE_BRICK_SLAB_DOUBLE).setDropped(ModBlocks.PRISMARINE_BRICK_SLAB);

//		((StandingSign)ACACIA_SIGN_STANDING).setItemSign(ModItems.ACACIA_SIGN);
//		((StandingSign)BIRCH_SIGN_STANDING).setItemSign(ModItems.BIRCH_SIGN);
//		((StandingSign)DARK_OAK_SIGN_STANDING).setItemSign(ModItems.DARK_OAK_SIGN);
//		((StandingSign)JUNGLE_SIGN_STANDING).setItemSign(ModItems.JUNGLE_SIGN);
//		((StandingSign)SPRUCE_SIGN_STANDING).setItemSign(ModItems.SPRUCE_SIGN);
//
//		((WallSign)ACACIA_SIGN_WALL).setItemSign(ModItems.ACACIA_SIGN);
//		((WallSign)BIRCH_SIGN_WALL).setItemSign(ModItems.BIRCH_SIGN);
//		((WallSign)DARK_OAK_SIGN_WALL).setItemSign(ModItems.DARK_OAK_SIGN);
//		((WallSign)JUNGLE_SIGN_WALL).setItemSign(ModItems.JUNGLE_SIGN);
//		((WallSign)SPRUCE_SIGN_WALL).setItemSign(ModItems.SPRUCE_SIGN);
	}

}
