package com.momo.fd.blocks.blockInteractive;

import com.momo.fd.blocks.BlockBase;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.material.Material;

public class BlockInteractiveBase extends BlockBase implements IHasModel {

    public BlockInteractiveBase(String name, Material material) {
        super(name, material);
        setLightOpacity(0);
    }
}
