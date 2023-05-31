package com.momo.fd.world.gen;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModGenInit {
    public static void registerGens()
    {
        //Please register this in preInitRegistries
        //GameRegistry.registerWorldGenerator(new ModWorldGenOld(), 100);
        GameRegistry.registerWorldGenerator(new ModWorldGenNew(), 120);
        GameRegistry.registerWorldGenerator(new ModPlantGen(), 140);
        GameRegistry.registerWorldGenerator(new ModLushCaveGen(), 160);
        GameRegistry.registerWorldGenerator(new ModDeepslateGen(), 170);
        GameRegistry.registerWorldGenerator(new ModAmethystGen(), 180);
    }
}
