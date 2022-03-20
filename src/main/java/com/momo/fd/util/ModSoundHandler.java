package com.momo.fd.util;

import com.momo.fd.MoMoFramework;
import com.momo.fd.util.sound.ModSoundEvent;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModSoundHandler {
    //To add a sound, remember assets.idlframewok.sounds.json
    public static final List<ModSoundEvent> SOUNDS = new ArrayList<>();

//    public static SoundEvent SOUND_1 = new ModSoundEvent("entity.moroon.ambient");
//    public static SoundEvent SOUND_2 = new ModSoundEvent("entity.moroon.hurt");

    public static void soundRegister()
    {
        MoMoFramework.Log("Registering %s sounds.", SOUNDS.size());
        ForgeRegistries.SOUND_EVENTS.registerAll(ModSoundHandler.SOUNDS.toArray(new SoundEvent[0]));
        MoMoFramework.Log("Registered %s sounds.", SOUNDS.size());
    }

}
