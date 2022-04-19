package com.momo.fd.proxy;

import com.momo.fd.entity.ModEntityInit;
import com.momo.fd.entity.tiles.ModTileEntityItemStackRenderer;
import com.momo.fd.item.ItemVariantBase;
import com.momo.fd.item.shield.ItemShieldBase;
import com.momo.fd.keys.KeyboardManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy extends ProxyBase {
    public static final List<KeyBinding> KEY_BINDINGS = new ArrayList<KeyBinding>();
	//public static final KeyBinding CAST_MAINHAND = new ModKeyBinding("activate_skill_mainhand", KeyConflictContext.IN_GAME, KeyModifier.NONE, Keyboard.KEY_R, "key.category.idlframewok");
	//public static final KeyBinding CAST_OFFHAND = new ModKeyBinding("activate_skill_offhand", KeyConflictContext.IN_GAME, KeyModifier.NONE, Keyboard.KEY_GRAVE, "key.category.idlframewok");

	@Override
	public void onPreInit(FMLPreInitializationEvent event) {
		ModEntityInit.registerTiles();
	}

	@Override
	public void init(FMLInitializationEvent event){
		KeyboardManager.init();
	}

	public boolean isServer()
	{
		return false;
	}

	public void registerItemRenderer(Item item, int meta, String id)
	{
		if (item instanceof ItemVariantBase){
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName() + "_" + meta, id));
		}else {
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
		}

		if(item instanceof ItemShieldBase){
			item.setTileEntityItemStackRenderer(new ModTileEntityItemStackRenderer());}
	}

}
