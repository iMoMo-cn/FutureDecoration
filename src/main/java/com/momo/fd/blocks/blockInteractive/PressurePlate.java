package com.momo.fd.blocks.blockInteractive;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class PressurePlate extends BlockPressurePlate implements IHasModel {
    private static Sensitivity sensitivity = BlockPressurePlate.Sensitivity.EVERYTHING;

    public PressurePlate(String name, Material materialIn) {
        super(materialIn, sensitivity);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);

        this.setHardness(0.5F);
        this.setSoundType(SoundType.WOOD);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
