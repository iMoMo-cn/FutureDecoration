package com.momo.fd.item;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.food.Berries;
import com.momo.fd.item.misc.Dye;
import com.momo.fd.item.tile.ModItemDoor;
import com.momo.fd.item.tile.ModItemSign;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	//public static final Item ITEM_NAME = new ItemBase("item_name").setCreativeTab(ModCreativeTab.CREATIVE_TAB);

	public static final Item RED_NETHERBRICK = new ItemBase("red_netherbrick").setCreativeTab(CreativeTabs.MISC);
	public static final Item COPPER_INGOT = new ItemBase("copper_ingot").setCreativeTab(CreativeTabs.MISC);
	public static final Item RAW_ORE = new ItemVariantBase("raw_ore", 3).setCreativeTab(CreativeTabs.MISC);

	public static final Item BERRIES = new Berries("sweet_berries", 2, 0.0F, false);

	public static final Item ACACIA_SIGN = new ModItemSign("acacia_sign");
	public static final Item BIRCH_SIGN = new ModItemSign("birch_sign");
	public static final Item DARK_OAK_SIGN = new ModItemSign("dark_oak_sign");
	public static final Item JUNGLE_SIGN = new ModItemSign("jungle_sign");
	public static final Item SPRUCE_SIGN = new ModItemSign("spruce_sign");
	public static final Item GOLD_DOOR = new ModItemDoor("gold_door");

	public static final Item DYE_WHITE = new Dye("dye_white", EnumDyeColor.WHITE);
	public static final Item DYE_BLACK = new Dye("dye_black", EnumDyeColor.BLACK);
	public static final Item DYE_BLUE = new Dye("dye_blue", EnumDyeColor.BLUE);
	public static final Item DYE_BROWN = new Dye("dye_brown", EnumDyeColor.BROWN);

	public static void init()
	{
		((ModItemSign)ACACIA_SIGN).setWallSign(ModBlocks.ACACIA_SIGN_WALL);
		((ModItemSign)ACACIA_SIGN).setStandingSign(ModBlocks.ACACIA_SIGN_STANDING);
		((ModItemSign)BIRCH_SIGN).setWallSign(ModBlocks.BIRCH_SIGN_WALL);
		((ModItemSign)BIRCH_SIGN).setStandingSign(ModBlocks.BIRCH_SIGN_STANDING);
		((ModItemSign)DARK_OAK_SIGN).setWallSign(ModBlocks.DARK_OAK_SIGN_WALL);
		((ModItemSign)DARK_OAK_SIGN).setStandingSign(ModBlocks.DARK_OAK_SIGN_STANDING);
		((ModItemSign)JUNGLE_SIGN).setWallSign(ModBlocks.JUNGLE_SIGN_WALL);
		((ModItemSign)JUNGLE_SIGN).setStandingSign(ModBlocks.JUNGLE_SIGN_STANDING);
		((ModItemSign)SPRUCE_SIGN).setWallSign(ModBlocks.SPRUCE_SIGN_WALL);
		((ModItemSign)SPRUCE_SIGN).setStandingSign(ModBlocks.SPRUCE_SIGN_STANDING);
		((ModItemDoor)GOLD_DOOR).setDoor(ModBlocks.GOLD_DOOR);
	}
}
