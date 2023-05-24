package com.momo.fd;

import com.momo.fd.feature.ModFeatureHandler;
import com.momo.fd.init.*;
import com.momo.fd.item.potions.PotionTypeBase;
import com.momo.fd.meta.MetaUtil;
import com.momo.fd.network.NetworkHandler;
import com.momo.fd.proxy.ProxyBase;
import com.momo.fd.util.CommonDef;
import com.momo.fd.util.Reference;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import org.apache.logging.log4j.Logger;

//Added 'little' steps for making weapons, tools and armor. Enjoy casting and striking as a blacksmith.


@Mod(modid = MoMoFramework.MODID, name = MoMoFramework.NAME, version = MoMoFramework.VERSION)//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class MoMoFramework {
    public static final String MODID = "fd";
    public static final String NAME = "FutureDecoration";
    public static final String VERSION = "1.2.5";

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static MoMoFramework instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ProxyBase proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        if (MODID.equals("untitled")) {
            logger.error("Please change your mod id in the main class.");

        }

        if (Reference.CLIENT_PROXY_CLASS.indexOf("somebody.idlframewok.proxy.ClientProxy") > 0) {
            logger.warn("Have you changed your package name to author and modname?");

        }

        proxy.onPreInit(event);
        RegistryHandler.preInitRegistries(event);

    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {

        ModRecipes.init();
        RegistryHandler.initRegistries(event);
        PotionTypeBase.register();
        NetworkHandler.init();
        ModOreDict.init();
        ModFeatureHandler.init();
        ModFireInfo.init();
        proxy.init(event);

        LogWarning("%s has finished its initializations", MODID);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Moved Spawning registry to last since forge doesn't auto-generate sub
        // "M' biomes until late
        if (ModConfig.SPAWN_CONF.SPAWN) {
            ModSpawn.registerSpawnList();
        }

        MetaUtil.isIDLLoaded = Loader.isModLoaded("idealland");
        MetaUtil.isIRRLoaded = Loader.isModLoaded("itemrender");
        MetaUtil.isLoaded_TiC = Loader.isModLoaded("tconstruct");
        MetaUtil.isLoaded_Slashblade = Loader.isModLoaded("flammpfeil.slashblade");
        MetaUtil.isLoaded_Botania = Loader.isModLoaded("botania");
        MetaUtil.isLoaded_DWeapon = Loader.isModLoaded("dweapon");
        MetaUtil.isLoaded_AOA3 = Loader.isModLoaded(CommonDef.MOD_NAME_AOA3);
        MetaUtil.isLoaded_GC = Loader.isModLoaded("galacticraftcore");
        MetaUtil.isLoaded_GOG = Loader.isModLoaded(CommonDef.MOD_NAME_GOG);

        TrashTalking();

        RegistryHandler.postInitReg();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }


    private void TrashTalking() {
        if (MetaUtil.isIDLLoaded)
        {
            MoMoFramework.Log("[Idealland Framework] Bow to Idealland.");
        }
        else {
            MoMoFramework.Log("[Idealland Framework] Made with Idealland Framework.");
        }
    }

    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
    }

    public static void Log(String str) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(str);
//        }
    }

    public static void Log(String str, Object... args) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(String.format(str, args));
//        }
    }

}