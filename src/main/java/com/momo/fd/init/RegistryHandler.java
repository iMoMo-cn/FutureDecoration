package com.momo.fd.init;

import com.momo.fd.enchantments.ModEnchantmentInit;
import com.momo.fd.entity.renders.RenderHandler;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.entity.ModEntityInit;
import com.momo.fd.item.ModItems;
import com.momo.fd.item.potions.ModPotionType;
import com.momo.fd.network.NetworkHandler;
import com.momo.fd.potion.ModPotion;
import com.momo.fd.util.IHasModel;
import com.momo.fd.util.sound.ModSoundHandler;
import com.momo.fd.worldgen.ModGenInit;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		ModItems.init();
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		ModBlocks.init();
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event)
	{
		ModEnchantmentInit.BeforeRegister();
		event.getRegistry().registerAll(ModEnchantmentInit.ENCHANTMENT_LIST.toArray(new Enchantment[0]));
	}

	@SubscribeEvent
	public static void onPotionRegistry(RegistryEvent.Register<Potion> event)
	{
		event.getRegistry().registerAll(ModPotion.POTIONS.toArray(new Potion[0]));
	}

	@SubscribeEvent
	public static void onPotionTypeRegistry(RegistryEvent.Register<PotionType> event)
	{
		event.getRegistry().registerAll(ModPotionType.POTION_TYPES.toArray(new PotionType[0]));
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ModItems.ITEMS)
		{
			if (item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : ModBlocks.BLOCKS)
		{
			if (block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}

		RenderHandler.registerEntityRenders();
	}

	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		InitBiome.registerBiomes();
		InitDimension.registerDimensions();

        ModGenInit.registerGens();

		ModEntityInit.registerEntities();
		ModEntityInit.registerTiles();

		NetworkHandler.register();

	}

	public static void postInitReg()
	{
		//WorldType TYPE_ONE = new WorldTypeOne();
	}

	public static void initRegistries(FMLInitializationEvent event)
	{
		ModSoundHandler.soundRegister();
	}

	public static void serverRegistries(FMLServerStartingEvent event)
    {
        //event.registerServerCommand(new CommandDimTeleport());
    }
}
