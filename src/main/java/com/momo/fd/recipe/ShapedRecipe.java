package com.momo.fd.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nonnull;

public class ShapedRecipe extends ShapedOreRecipe
{
    public ShapedRecipe(Block result, Object... recipe) { this(new ItemStack(result), recipe); }
    public ShapedRecipe(Item result, Object... recipe) { this(new ItemStack(result), recipe); }
    public ShapedRecipe(@Nonnull ItemStack result, Object... recipe) { this(result.getItem().getRegistryName(), result, CraftingHelper.parseShaped(recipe)); }

    ShapedRecipe(ResourceLocation group, @Nonnull ItemStack result, net.minecraftforge.common.crafting.CraftingHelper.ShapedPrimer primer)
    {
        super(group, result, primer);
    }
}
