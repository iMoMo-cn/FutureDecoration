package com.momo.fd.init;

import com.momo.fd.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ModFireInfo {

/**
 * Chance that fire will spread and consume this block. 300 being a 100% chance, 0, being a 0% chance.
 **/
    public static void init()
    {
        //minecraft
        setFireInfo(Blocks.LADDER, 5, 20);
        setFireInfo(Blocks.SAPLING, 30, 60);
        setFireInfo(Blocks.WHEAT, 60, 100);
        setFireInfo(Blocks.CARROTS, 60, 100);
        setFireInfo(Blocks.BEETROOTS, 60, 100);
        setFireInfo(Blocks.POTATOES, 60, 100);
        setFireInfo(Blocks.PUMPKIN_STEM, 60, 100);
        setFireInfo(Blocks.MELON_STEM, 60, 100);
        setFireInfo(Blocks.PUMPKIN, 5, 20);
        setFireInfo(Blocks.MELON_BLOCK, 5, 20);

        //mod
        setFireInfo(ModBlocks.CHARCOAL_BLOCK, 5, 5);
        setFireInfo(ModBlocks.WOOD_BARS, 5, 20);

        setFireInfo(ModBlocks.PUMPKIN_STEM, 60, 100);
        setFireInfo(ModBlocks.PUMPKIN, 5, 20);
        setFireInfo(ModBlocks.CAVE_VINE, 15, 100);
        setFireInfo(ModBlocks.CAVE_VINE_PLANT, 15, 100);
        setFireInfo(ModBlocks.BERRY_BUSH, 30, 60);
        setFireInfo(ModBlocks.SAVANNA_BERRY_BUSH, 30, 60);

        setFireInfo(ModBlocks.STRIPPED_ACACIA, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_BIRCH, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_DARK_OAK, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_JUNGLE, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_OAK, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_SPRUCE, 5, 5);

        setFireInfo(ModBlocks.STRIPPED_ACACIA_WOOD, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_BIRCH_WOOD, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_DARK_OAK_WOOD, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_JUNGLE_WOOD, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_OAK_WOOD, 5, 5);
        setFireInfo(ModBlocks.STRIPPED_SPRUCE_WOOD, 5, 5);

        setFireInfo(ModBlocks.WOOD_ACACIA, 5, 5);
        setFireInfo(ModBlocks.WOOD_BIRCH, 5, 5);
        setFireInfo(ModBlocks.WOOD_DARK_OAK, 5, 5);
        setFireInfo(ModBlocks.WOOD_JUNGLE, 5, 5);
        setFireInfo(ModBlocks.WOOD_OAK, 5, 5);
        setFireInfo(ModBlocks.WOOD_SPRUCE, 5, 5);

        setFireInfo(ModBlocks.CARPET, 60, 20);
        setFireInfo(ModBlocks.TIGHT_CARPET, 60, 20);

        setFireInfo(ModBlocks.AZALEA_LEAVES, 30, 60);
        setFireInfo(ModBlocks.FLOWERING_AZALEA_LEAVES, 30, 60);
        setFireInfo(ModBlocks.AZALEA, 30, 60);
        setFireInfo(ModBlocks.HANGING_ROOTS, 30, 60);
    }

    public static void setFireInfo(Block block, int encouragement, int flammability)
    {
        Blocks.FIRE.setFireInfo(block, encouragement, flammability);
    }
}
