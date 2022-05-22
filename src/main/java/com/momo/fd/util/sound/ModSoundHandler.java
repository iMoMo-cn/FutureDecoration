package com.momo.fd.util.sound;

import com.momo.fd.MoMoFramework;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModSoundHandler {
    //To add a sound, remember assets.mmf.sounds.json
    public static final List<ModSoundEvent> SOUNDS = new ArrayList<>();

    public static SoundEvent BLOCK_LANTER_BREAK = new ModSoundEvent("block.lantern.break");
    public static SoundEvent BLOCK_LANTER_STEP = new ModSoundEvent("block.lantern.step");
    public static SoundEvent BLOCK_LANTER_PLACE = new ModSoundEvent("block.lantern.place");
    public static SoundEvent BLOCK_LANTER_HIT = new ModSoundEvent("block.lantern.hit");
    public static SoundEvent BLOCK_LANTER_FALL = new ModSoundEvent("block.lantern.fall");

    public static SoundEvent BLOCK_CHAIN_BREAK = new ModSoundEvent("block.chain.break");
    public static SoundEvent BLOCK_CHAIN_STEP = new ModSoundEvent("block.chain.step");
    public static SoundEvent BLOCK_CHAIN_PLACE = new ModSoundEvent("block.chain.place");
    public static SoundEvent BLOCK_CHAIN_HIT = new ModSoundEvent("block.chain.hit");
    public static SoundEvent BLOCK_CHAIN_FALL = new ModSoundEvent("block.chain.fall");

    public static SoundEvent STRIP_WOOD = new ModSoundEvent("item.axe.strip");

    public static void soundRegister()
    {
        MoMoFramework.Log("Registering %s sounds.", SOUNDS.size());
        ForgeRegistries.SOUND_EVENTS.registerAll(ModSoundHandler.SOUNDS.toArray(new SoundEvent[0]));
        MoMoFramework.Log("Registered %s sounds.", SOUNDS.size());
    }

}
