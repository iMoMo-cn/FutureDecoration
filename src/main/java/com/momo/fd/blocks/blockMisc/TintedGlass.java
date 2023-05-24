package com.momo.fd.blocks.blockMisc;

import com.momo.fd.blocks.blockBasic.decoration.Glass;

import java.util.Random;

public class TintedGlass extends Glass {
    public TintedGlass(String name) {
        super(name);

        setLightOpacity(15);
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

}
