package com.momo.fd.entity;

import com.momo.fd.blocks.blockTile.TileEntityBarrel;
import com.momo.fd.blocks.blockTile.TileEntityBlastFurnace;
import com.momo.fd.blocks.blockTile.TileEntityComposter;
import com.momo.fd.blocks.blockTile.TileEntitySmoker;
import com.momo.fd.blocks.blockTile.tileSign.*;
import com.momo.fd.util.Reference;
import com.momo.fd.MoMoFramework;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;


public class ModEntityInit {
    private static int ENTITY_NEXT_ID = 1;

    public static void registerEntities()
    {
        /*
        Examples  (EntityList)
        registerEntity("name", EntityName.class);
        registerEntity("name", EntityName.class, eggPrimaryColor, eggSecondaryColor);
        registerEntity("name", EntityName.class, range, eggPrimaryColor, eggSecondaryColor);
        registerThrowable("name", EntityName.class);
         */


        DataFixer datafixer = new DataFixer(1343);
    }

    public static void registerTiles()
    {
        registerTile(TileEntitySignAcacia.class, "fd:sign_acacia");
        registerTile(TileEntitySignBirch.class, "fd:sign_birch");
        registerTile(TileEntitySignDarkOak.class,"fd:sign_dark_oak");
        registerTile(TileEntitySignJungle.class, "fd:sign_jungle");
        registerTile(TileEntitySignSpruce.class, "fd:sign_spruce");
        registerTile(TileEntityBarrel.class, "fd:barrel");
        registerTile(TileEntityComposter.class, "fd:composter");
        registerTile(TileEntitySmoker.class,  "fd:smoker");
        registerTile(TileEntityBlastFurnace.class,  "fd:blast_furnace");
    }

    public static void registerTile(Class<? extends TileEntity> tileEntityClass, String key)
    {
        GameData.checkPrefix(new ResourceLocation(key).toString());
        TileEntity.register(key, tileEntityClass);
    }

    @SideOnly(Side.CLIENT)
    public static void tileEntitySpecialRenderer()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySignAcacia.class, new SignAcaciaRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySignBirch.class, new SignBirchRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySignDarkOak.class, new SignDarkOakRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySignJungle.class, new SignJungleRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySignSpruce.class, new SignSpruceRender());
    }

    private static void registerEntity(String name, Class<? extends Entity> entity)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, 0xff00ff, 0x000000);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int eggPrimaryColor, int eggSecondaryColor)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, 50, eggPrimaryColor, eggSecondaryColor);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int range, int eggPrimaryColor, int eggSecondaryColor)
    {
        registerEntity(name, entity, ENTITY_NEXT_ID, range, eggPrimaryColor, eggSecondaryColor);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int eggPrimaryColor, int eggSecondaryColor){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name),
                entity, name, id, MoMoFramework.instance, range, 1, true,
                eggPrimaryColor, eggSecondaryColor);
        ENTITY_NEXT_ID++;
    }

    //throwable registry
    private static void registerThrowable(String name, Class<? extends Entity> entity){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name),
                entity, name, ENTITY_NEXT_ID, MoMoFramework.instance, 64, 10, true);
        ENTITY_NEXT_ID++;
    }

}
