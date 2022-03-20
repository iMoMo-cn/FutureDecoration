package com.momo.fd.entity;

import com.momo.fd.util.Reference;
import com.momo.fd.MoMoFramework;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntityInit {
    private static int ENTITY_NEXT_ID = 1;
    public static void registerEntities()
    {
        /*
        Examples  (EntityList)
        registerEntity("name", EntityName.class);
        registerEntity("name", EntityName.class, eggPrimaryColor, eggSecondaryColor);
        registerEntity("name", EntityName.class, range, eggPrimaryColor, eggSecondaryColor);
        registerThrowable("name", EntityName.class, ENTITY_NEXT_ID);
         */

        DataFixer datafixer = new DataFixer(1343);
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
    private static void registerThrowable(String name, Class<? extends Entity> entity, int id){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name),
                entity, name, id, MoMoFramework.instance, 64, 10, true);
        ENTITY_NEXT_ID++;
    }

//    public static final List<EntityEntry> THROWABLE = new ArrayList<>();
//
//    private static void registerThrowable(Class<? extends Entity> entity, String name, String Name){
//        EntityEntry Throwable = EntityEntryBuilder.create().entity(entity).id(name, 6).name(Name)
//                .tracker(64, 10, true).build();
//        THROWABLE.add(Throwable);
//    }
}
