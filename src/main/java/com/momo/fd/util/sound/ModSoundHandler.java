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

    public static SoundEvent BLOCK_BARREL_OPEN = new ModSoundEvent("block.barrel.open");
    public static SoundEvent BLOCK_BARREL_CLOSE = new ModSoundEvent("block.barrel.close");

    public static SoundEvent STRIP_WOOD = new ModSoundEvent("item.axe.strip");

    public static SoundEvent BLOCK_BERRY_BREAK = new ModSoundEvent("block.sweet_berry_bush.break");
    public static SoundEvent BLOCK_BERRY_PLACE = new ModSoundEvent("block.sweet_berry_bush.place");

    public static SoundEvent PICK_BERRIES = new ModSoundEvent("item.sweet_berries.pick_from_bush");

    public static SoundEvent BLOCK_COMPOSTER_EMPTY = new ModSoundEvent("block.composter.empty");
    public static SoundEvent BLOCK_COMPOSTER_FILL = new ModSoundEvent("block.composter.fill");
    public static SoundEvent BLOCK_COMPOSTER_FILL_SUCCESS = new ModSoundEvent("block.composter.fill_success");
    public static SoundEvent BLOCK_COMPOSTER_REDAY = new ModSoundEvent("block.composter.ready");

    public static SoundEvent BLOCK_DEEPSLATE_BREAK = new ModSoundEvent("block.deepslate.break");
    public static SoundEvent BLOCK_DEEPSLATE_STEP = new ModSoundEvent("block.deepslate.step");
    public static SoundEvent BLOCK_DEEPSLATE_PLACE = new ModSoundEvent("block.deepslate.place");
    public static SoundEvent BLOCK_DEEPSLATE_HIT = new ModSoundEvent("block.deepslate.hit");
    public static SoundEvent BLOCK_DEEPSLATE_FALL = new ModSoundEvent("block.deepslate.fall");

    public static SoundEvent BLOCK_DEEPSLATE_BRICKS_BREAK = new ModSoundEvent("block.deepslate_bricks.break");
    public static SoundEvent BLOCK_DEEPSLATE_BRICKS_STEP = new ModSoundEvent("block.deepslate_bricks.step");
    public static SoundEvent BLOCK_DEEPSLATE_BRICKS_PLACE = new ModSoundEvent("block.deepslate_bricks.place");
    public static SoundEvent BLOCK_DEEPSLATE_BRICKS_HIT = new ModSoundEvent("block.deepslate_bricks.hit");
    public static SoundEvent BLOCK_DEEPSLATE_BRICKS_FALL = new ModSoundEvent("block.deepslate_bricks.fall");

    public static SoundEvent BLOCK_CAVE_VINES_BREAK = new ModSoundEvent("block.cave_vines.break");
    public static SoundEvent BLOCK_CAVE_VINES_STEP = new ModSoundEvent("block.cave_vines.step");
    public static SoundEvent BLOCK_CAVE_VINES_PLACE = new ModSoundEvent("block.cave_vines.place");
    public static SoundEvent BLOCK_CAVE_VINES_HIT = new ModSoundEvent("block.cave_vines.hit");
    public static SoundEvent BLOCK_CAVE_VINES_FALL = new ModSoundEvent("block.cave_vines.fall");

    public static SoundEvent BLOCK_PUMPKIN_CARVE = new ModSoundEvent("block.pumpkin.carve");

    public static SoundEvent BLOCK_AMETHYST_CHIME = new ModSoundEvent("block.amethyst_block.chime");

    public static SoundEvent BLOCK_AMETHYST_BREAK = new ModSoundEvent("block.amethyst_block.break");
    public static SoundEvent BLOCK_AMETHYST_STEP = new ModSoundEvent("block.amethyst_block.step");
    public static SoundEvent BLOCK_AMETHYST_PLACE = new ModSoundEvent("block.amethyst_block.place");
    public static SoundEvent BLOCK_AMETHYST_HIT = new ModSoundEvent("block.amethyst_block.hit");
    public static SoundEvent BLOCK_AMETHYST_FALL = new ModSoundEvent("block.amethyst_block.fall");

    public static SoundEvent BLOCK_AMETHYST_CLUSTER_BREAK = new ModSoundEvent("block.amethyst_cluster.break");
    public static SoundEvent BLOCK_AMETHYST_CLUSTER_STEP = new ModSoundEvent("block.amethyst_cluster.step");
    public static SoundEvent BLOCK_AMETHYST_CLUSTER_PLACE = new ModSoundEvent("block.amethyst_cluster.place");
    public static SoundEvent BLOCK_AMETHYST_CLUSTER_HIT = new ModSoundEvent("block.amethyst_cluster.hit");
    public static SoundEvent BLOCK_AMETHYST_CLUSTER_FALL = new ModSoundEvent("block.amethyst_cluster.fall");


    public static void soundRegister()
    {
        MoMoFramework.Log("Registering %s sounds.", SOUNDS.size());
        ForgeRegistries.SOUND_EVENTS.registerAll(ModSoundHandler.SOUNDS.toArray(new SoundEvent[0]));
        MoMoFramework.Log("Registered %s sounds.", SOUNDS.size());
    }

}
