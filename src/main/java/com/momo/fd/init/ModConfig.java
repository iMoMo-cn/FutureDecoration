package com.momo.fd.init;

import com.momo.fd.util.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Config(modid = Reference.MOD_ID, category = "")
public class ModConfig {
    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    private static class EventHandler {

        private EventHandler() {
        }

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Reference.MOD_ID)) {
                ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }

    @Config.LangKey("configgui.idlframewok.category.Menu0.GeneralConf")
    @Config.Comment("MoMoFramework general config.")
    public static final GeneralConf GENERAL_CONF = new GeneralConf();

    public static class GeneralConf {
//        @Config.LangKey("idlframewok.conf.general.welcome")
//        @Config.Comment("The text shown when a player logs in. Can be a key or a string.")
//        public String WELCOME_MSG = "idlframewok.msg.welcome";
        //this one disables it from the registry part, has better performance.
        @Config.LangKey("configgui.idlframewok.category.Menu0.GeneralConf.use_world_gen")
        @Config.RequiresMcRestart
        public boolean USE_WORLD_GEN = true;

        //this one disables it on the go, which is more flexible, but has a small overhead.
        @Config.LangKey("configgui.idlframewok.category.Menu0.GeneralConf.create_deep_slate")
        public boolean CREATE_DEEP_SLATE = true;
    }

    @Config.LangKey("configgui.idlframewok.category.Menu0.DebugConf")
    @Config.Comment("Config for developers")
    public static final DebugConf DEBUG_CONF = new DebugConf();

    public static class DebugConf {

    }

    @Config.LangKey("configgui.idlframewok.category.Menu0.SpawnConf")
    @Config.Comment("Spawning")
    public static final SpawnConf SPAWN_CONF = new SpawnConf();

    public static class SpawnConf {
        @Config.LangKey("conf.spawn.enabled")
        @Config.Comment("Spawn mod creatures")
        @Config.RequiresMcRestart
        public boolean SPAWN = true;

//        @Config.LangKey("entity.moroon_tainter.name")
//        @Config.Comment("Spawn Moroon Tainter")
//        @Config.RequiresMcRestart
//        public int SPAWN_TAINTER = 100;
    }
}
