package com.momo.fd.meta;

import net.minecraftforge.fml.common.Loader;

public class MetaUtil {
    public static boolean isIDLLoaded = false;

    //static int modListDifficulty = 0;
    static int modListExtraDifficulty = 0;

    public static int getModListExtraDifficulty() {
        return modListExtraDifficulty;
    }

    public static void CalcModListDifficulty()
    {
       //modListDifficulty = CommonFunctions.GetModCount();

    }

    public static int GetModCount()
    {
        return Loader.instance().getActiveModList().size();
    }
}
