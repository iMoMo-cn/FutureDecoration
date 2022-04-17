package com.momo.fd.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import javax.annotation.Nonnull;

public class ShapelessRecipe extends ShapelessOreRecipe
{
    public ShapelessRecipe(Block result, Object... recipe) { this(new ItemStack(result), recipe); }
    public ShapelessRecipe(Item result, Object... recipe) { this(new ItemStack(result), recipe); }

    public ShapelessRecipe(@Nonnull ItemStack result, NonNullList<Ingredient> input)
    {
        super(result.getItem().getRegistryName(), input, result);
    }

    public ShapelessRecipe(@Nonnull ItemStack result, Object... recipe)
    {
        super(result.getItem().getRegistryName(), result, recipe);
    }
}
