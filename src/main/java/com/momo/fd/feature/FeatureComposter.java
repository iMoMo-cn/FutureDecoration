package com.momo.fd.feature;

import com.momo.fd.blocks.blockInteractive.Composter;
import com.momo.fd.util.sound.ModSoundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

public class FeatureComposter {

    public static Map<String, Float> inputs = new HashMap<>();

    public static void setupConfig()
    {
        String[] items;

        items = new String[]{
                "minecraft:beetroot_seeds",
                "minecraft:tallgrass",
                "minecraft:leaves",
                "minecraft:leaves2",
                "minecraft:melon_seeds",
                "minecraft:pumpkin_seeds",
                "minecraft:sapling",
                "minecraft:wheat_seeds",
                "fd:sweet_berries",
                "fd:soul_berries",
                "fd:moss_carpet[0]",
                "fd:moss_carpet[1]",
                "fd:moss_carpet[2]",
                "fd:azalea_leaves",
                "fd:flowering_azalea_leaves",
                "fd:hanging_roots"
        };
        for (String item : items) inputs.put(item, 0.3f);

        items = new String[] {
                "minecraft:cactus",
                "minecraft:melon",
                "minecraft:reeds",
                "minecraft:double_plant[2]",
                "minecraft:vine",
                "fd:glow_berries"
        };
        for (String item : items) inputs.put(item, 0.5f);

        items = new String[] {
                "minecraft:apple",
                "minecraft:beetroot",
                "minecraft:carrot",
                "minecraft:dye[3]",
                "minecraft:tallgrass[2]",
                "minecraft:double_plant[3]",
                "minecraft:yellow_flower",
                "minecraft:double_plant",
                "minecraft:double_plant[1]",
                "minecraft:double_plant[4]",
                "minecraft:double_plant[5]",
                "minecraft:red_flower",
                "minecraft:waterlily",
                "minecraft:melon_block",
                "minecraft:red_mushroom",
                "minecraft:brown_mushroom",
                "minecraft:potato",
                "minecraft:poisonous_potato",
                "minecraft:pumpkin",
                "minecraft:wheat",
                "minecraft:nether_wart",
                "fd:pumpkin",
                "fd:cornflower",
                "fd:lily_of_the_valley",
                "fd:rose",
                "fd:wither_rose",
                "fd:pumpkin",
                "fd:moss_block",
                "fd:azalea[0]"
        };
        for (String item : items) inputs.put(item, 0.65f);

        items = new String[] {
                "minecraft:baked_potato",
                "minecraft:bread",
                "minecraft:cookie",
                "minecraft:hay_block",
                "minecraft:red_mushroom_block",
                "minecraft:brown_mushroom_block",
                "fd:azalea[1]"
        };
        for (String item : items) inputs.put(item, 0.85f);

        items = new String[] {
                "minecraft:cake",
                "minecraft:pumpkin_pie"
        };
        for (String item : items) inputs.put(item, 1.0f);
    }

    @SideOnly(Side.CLIENT)
    public static void levelAdded(BlockPos pos, int newLevel, int level)
    {
        WorldClient world = Minecraft.getMinecraft().world;
        SoundEvent sound;
        if(newLevel == -1)
        {
            sound = ModSoundHandler.BLOCK_COMPOSTER_EMPTY;
        }
        else if(newLevel != level)
        {
            sound = newLevel == 8 ? ModSoundHandler.BLOCK_COMPOSTER_REDAY : ModSoundHandler.BLOCK_COMPOSTER_FILL_SUCCESS;
        }
        else {
            sound = ModSoundHandler.BLOCK_COMPOSTER_FILL;
        }
        world.playSound(pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
    }
}
