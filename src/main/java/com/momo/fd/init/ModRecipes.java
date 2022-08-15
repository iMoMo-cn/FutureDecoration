package com.momo.fd.init;

import com.google.common.collect.Sets;
import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import java.util.Set;

import static com.momo.fd.MoMoFramework.Log;
import static com.momo.fd.MoMoFramework.LogWarning;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModRecipes {

	public static Set<ResourceLocation> recipes = Sets.newHashSet();

	public static void init() {
		//Only smelting recipes
		//GameRegistry.addSmelting(ModItems.XXXX, new ItemStack(ModItems.XXXX), 0.3f);
		GameRegistry.addSmelting(new ItemStack(Blocks.STONE, 1, 0), new ItemStack(ModBlocks.SMOOTH_STONE), 0.3f);
		GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(ModBlocks.CLEAR_GLASS), 0.3f);
		GameRegistry.addSmelting(ModBlocks.BLACKSTONE_BRICK, new ItemStack(ModBlocks.CRACKED_BLACKSTONE_BRICK), 0.3f);
		GameRegistry.addSmelting(Blocks.NETHER_BRICK, new ItemStack(ModBlocks.CRACKED_NETHER_BRICK), 0.3f);
		GameRegistry.addSmelting(Blocks.RED_NETHER_BRICK, new ItemStack(ModBlocks.CRACKED_RED_NETHER_BRICK), 0.3f);
		GameRegistry.addSmelting(new ItemStack(Blocks.SANDSTONE, 1, 0), new ItemStack(ModBlocks.SMOOTH_SANDSTONE), 0.3f);
		GameRegistry.addSmelting(new ItemStack(Blocks.RED_SANDSTONE, 1, 0), new ItemStack(ModBlocks.SMOOTH_RED_SANDSTONE), 0.3f);
	}


	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> evt) {
		IForgeRegistry<IRecipe> r = evt.getRegistry();
		IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) r;

		//Example
		//r.register(new Recipe().setRegistryName(new ResourceLocation(Reference.MOD_ID, "recipe")));

		//remove recipe
		removeRecipes(registry);

	}
	private static void removeRecipes(IForgeRegistryModifiable<IRecipe> registry){
		removeRecipe(new ItemStack(Blocks.TRAPDOOR, 2));
		removeRecipe(new ItemStack(Blocks.WOODEN_BUTTON, 1));
		removeRecipe(new ItemStack(Blocks.NETHER_BRICK_FENCE, 3));
		removeRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 0));
		removeRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 5));
		removeRecipe(new ItemStack(Blocks.WOODEN_PRESSURE_PLATE, 1));
		removeRecipe(new ItemStack(Items.SIGN, 3));
		removeRecipe(new ItemStack(Blocks.STONE_SLAB, 6, 1));
		removeRecipe(new ItemStack(Blocks.STONE_SLAB2, 6, 0));
		removeRecipe(new ItemStack(Blocks.SANDSTONE_STAIRS, 4));
		removeRecipe(new ItemStack(Blocks.RED_SANDSTONE_STAIRS, 4));

		recipes.forEach(rl -> {
			if(!rl.getResourceDomain().equals(MoMoFramework.MODID)) {
				LogWarning("Removing: " + rl);
				registry.remove(rl);
			}
		});
	}

	private static void removeRecipe(ItemStack resultItem){
		CraftingManager.REGISTRY.forEach((recipe) -> {
			if(ItemStack.areItemsEqual(recipe.getRecipeOutput(), resultItem)) {
					recipes.add(recipe.getRegistryName());
			}
		});
	}
}
