package com.momo.fd.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyBase {
	public void onPreInit(FMLPreInitializationEvent event) {}

	public void init(FMLInitializationEvent event){}

	public boolean isServer()
	{
		return false;
	}

	public void registerItemRenderer(Item item, int meta, String id) {
		//Ignored
	}
}
