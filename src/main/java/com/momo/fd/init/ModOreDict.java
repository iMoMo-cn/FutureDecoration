package com.momo.fd.init;

import com.momo.fd.blocks.ModBlocks;
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

        //MOD OreDic
        OreDictionary.registerOre("blockCharcoal", ModBlocks.CHARCOAL_BLOCK);
    }
}
