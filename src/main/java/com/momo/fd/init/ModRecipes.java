package com.momo.fd.init;

import com.google.common.collect.Sets;
import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
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

		//smooth block
		GameRegistry.addSmelting(new ItemStack(Blocks.STONE, 1, 0), new ItemStack(ModBlocks.ROCK_BLOCK, 1, 0), 0.3f);
		GameRegistry.addSmelting(new ItemStack(Blocks.SANDSTONE, 1, 0), new ItemStack(ModBlocks.SANDSTONE, 1, 0), 0.3f);
		GameRegistry.addSmelting(new ItemStack(Blocks.RED_SANDSTONE, 1, 0), new ItemStack(ModBlocks.SANDSTONE, 1, 1), 0.3f);
		GameRegistry.addSmelting(new ItemStack(Blocks.QUARTZ_BLOCK), new ItemStack(ModBlocks.NETHER_BLOCK, 1, 10), 0.3f);

		//cracked block
		GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_BLOCK, 1, 3), new ItemStack(ModBlocks.NETHER_BLOCK, 1, 4), 0.3f);
		GameRegistry.addSmelting(Blocks.NETHER_BRICK, new ItemStack(ModBlocks.NETHER_BLOCK, 1, 7), 0.3f);
		GameRegistry.addSmelting(Blocks.RED_NETHER_BRICK, new ItemStack(ModBlocks.NETHER_BLOCK, 1, 9), 0.3f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.DEEPSLATE_ROCK, 1, 2), new ItemStack(ModBlocks.DEEPSLATE_ROCK, 1, 5), 0.3f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.DEEPSLATE_ROCK, 1, 3), new ItemStack(ModBlocks.DEEPSLATE_ROCK, 1, 6), 0.3f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ROCK_BLOCK, 1, 1), new ItemStack(ModBlocks.ROCK_BLOCK, 1, 2), 0.3f);

		//misc
		GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(ModBlocks.CLEAR_GLASS), 0.3f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.DEEPSLATE_ROCK, 1, 0), new ItemStack(ModBlocks.DEEPSLATE), 0.3f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.BASALT), new ItemStack(ModBlocks.ROCK_BLOCK, 1, 7), 0.3f);

		//ore
		GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_ORE, 1, 0), new ItemStack(Items.GOLD_INGOT, 1), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.NETHER_ORE, 1, 1), new ItemStack(Items.IRON_INGOT, 1), 0.7f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.COPPER_ORE), new ItemStack(ModItems.COPPER_INGOT, 1), 0.7f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE, 1, 0), new ItemStack(Items.IRON_INGOT, 1), 0.7f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE, 1, 1), new ItemStack(Items.GOLD_INGOT, 1), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE, 1, 2), new ItemStack(ModItems.COPPER_INGOT, 1), 0.7f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE, 1, 3), new ItemStack(Items.COAL, 1), 0.1f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE, 1, 4), new ItemStack(Items.DYE, 1, 4), 0.2f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE, 1, 5), new ItemStack(Items.DIAMOND, 1), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.ORE, 1, 6), new ItemStack(Items.EMERALD, 1), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.DEEPSLATE_REDSTONE_ORE), new ItemStack(Items.REDSTONE, 1), 0.2f);

	    //raw ore
		GameRegistry.addSmelting(new ItemStack(ModItems.RAW_ORE, 1, 0), new ItemStack(Items.IRON_INGOT, 1), 0.7f);
		GameRegistry.addSmelting(new ItemStack(ModItems.RAW_ORE, 1, 1), new ItemStack(Items.GOLD_INGOT, 1), 1.0f);
		GameRegistry.addSmelting(new ItemStack(ModItems.RAW_ORE, 1, 2), new ItemStack(ModItems.COPPER_INGOT, 1), 0.7f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.RAW_ORE, 1, 0), new ItemStack(Blocks.IRON_BLOCK, 1), 7.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.RAW_ORE, 1, 1), new ItemStack(Blocks.GOLD_BLOCK, 1), 10.0f);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.RAW_ORE, 1, 2), new ItemStack(ModBlocks.COPPER_BLOCK, 1), 7.0f);
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
		removeRecipe(new ItemStack(Blocks.STONE_BRICK_STAIRS, 4));
		removeRecipe(new ItemStack(Items.PUMPKIN_SEEDS, 4));

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
