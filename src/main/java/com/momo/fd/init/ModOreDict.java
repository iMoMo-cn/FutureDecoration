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

        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_ACACIA);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_ACACIA_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.WOOD_ACACIA);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_BIRCH);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_BIRCH_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.WOOD_BIRCH);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_DARK_OAK);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_DARK_OAK_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.WOOD_DARK_OAK);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_JUNGLE);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_JUNGLE_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.WOOD_JUNGLE);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_OAK);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_OAK_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.WOOD_OAK);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_SPRUCE);
        OreDictionary.registerOre("logWood", ModBlocks.STRIPPED_SPRUCE_WOOD);
        OreDictionary.registerOre("logWood", ModBlocks.WOOD_SPRUCE);

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

        OreDictionary.registerOre("blockGlass", ModBlocks.CLEAR_GLASS);
        OreDictionary.registerOre("paneGlass", ModBlocks.CLEAR_PANE);

        for(int i = 0 ; i < 16; i++)
        {
            OreDictionary.registerOre("blockGlass", new ItemStack(ModBlocks.STAINED_CLEAR_GLASS, 1, i));
            OreDictionary.registerOre("paneGlass", new ItemStack(ModBlocks.STAINED_CLEAR_GLASS_PANE, 1, i));
        }

        for(int i = 0 ; i < 5; i++)
        {
            OreDictionary.registerOre("shardCrystal", new ItemStack(ModItems.CRYSTAL_SHARD, 1, i));
        }

        OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.DEEPSLATE_ROCK, 1, 0));
        OreDictionary.registerOre("cobblestone", new ItemStack(ModBlocks.NETHER_BLOCK, 1, 0));

        OreDictionary.registerOre("ingotCopper", ModItems.COPPER_INGOT);
    }
}
