package com.momo.fd.init;

import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDict
{
    public static void init()
    {
        //Minecraft OreDic
        OreDictionary.registerOre("charcoal", new ItemStack(Items.COAL, 1, 1));
        OreDictionary.registerOre("coal", new ItemStack(Items.COAL, 1, 32767));
        OreDictionary.registerOre("flint", Items.FLINT);

        //MOD OreDic
        OreDictionary.registerOre("blockCharcoal", ModBlocks.CHARCOAL_BLOCK);
        OreDictionary.registerOre("dyeWhite", ModItems.DYE_WHITE);
        OreDictionary.registerOre("dyeBrown", ModItems.DYE_BROWN);
        OreDictionary.registerOre("dyeBlue", ModItems.DYE_BLUE);
        OreDictionary.registerOre("dyeBlack", ModItems.DYE_BLACK);
        OreDictionary.registerOre("minecraft:buttons", ModBlocks.ACACIA_BUTTON);
        OreDictionary.registerOre("minecraft:buttons", ModBlocks.BIRCH_BUTTON);
        OreDictionary.registerOre("minecraft:buttons", ModBlocks.DRAK_OAK_BUTTON);
        OreDictionary.registerOre("minecraft:buttons", ModBlocks.JUNGLE_BUTTON);
        OreDictionary.registerOre("minecraft:buttons", ModBlocks.SPRUCE_BUTTON);
        OreDictionary.registerOre("minecraft:buttons", ModBlocks.BLACKSTONE_BUTTON);
        OreDictionary.registerOre("minecraft:wooden_buttons", ModBlocks.ACACIA_BUTTON);
        OreDictionary.registerOre("minecraft:wooden_buttons", ModBlocks.BIRCH_BUTTON);
        OreDictionary.registerOre("minecraft:wooden_buttons", ModBlocks.DRAK_OAK_BUTTON);
        OreDictionary.registerOre("minecraft:wooden_buttons", ModBlocks.JUNGLE_BUTTON);
        OreDictionary.registerOre("minecraft:wooden_buttons", ModBlocks.SPRUCE_BUTTON);
        OreDictionary.registerOre("minecraft:wooden_pressure_plates", ModBlocks.ACACIA_PRESSURE_PLATE);
        OreDictionary.registerOre("minecraft:wooden_pressure_plates", ModBlocks.BIRCH_PRESSURE_PLATE);
        OreDictionary.registerOre("minecraft:wooden_pressure_plates", ModBlocks.DRAK_OAK_PRESSURE_PLATE);
        OreDictionary.registerOre("minecraft:wooden_pressure_plates", ModBlocks.JUNGLE_PRESSURE_PLATE);
        OreDictionary.registerOre("minecraft:wooden_pressure_plates", ModBlocks.SPRUCE_PRESSURE_PLATE);
        OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.DEEPSLATE_ROCK, 1, 0));
        OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.NETHER_BLOCK, 1, 0));
    }
}
