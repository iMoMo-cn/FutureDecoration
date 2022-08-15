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
    }
}
