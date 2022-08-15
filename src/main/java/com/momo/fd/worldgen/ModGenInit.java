package com.momo.fd.worldgen;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModGenInit {
    public static void registerGens()
    {
        //GameRegistry.registerWorldGenerator(new ModWorldGenOld(), 100);
        //GameRegistry.registerWorldGenerator(new ModWorldGenNew(), 120);
        GameRegistry.registerWorldGenerator(new ModPlantGen(), 0);

    }
}
