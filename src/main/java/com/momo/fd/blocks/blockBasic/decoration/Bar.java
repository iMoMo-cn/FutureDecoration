package com.momo.fd.blocks.blockBasic.decoration;

import com.momo.fd.MoMoFramework;
import com.momo.fd.blocks.ModBlocks;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.block.BlockPane;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class Bar extends BlockPane implements IHasModel {
    public Bar(String name, Material materialIn, SoundType soundTypeIn) {
        super(materialIn, true);

        setUnlocalizedName(name);
        setRegistryName(name);

        setSoundType(soundTypeIn);

        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this){
                    @Override
                    public int getItemBurnTime(ItemStack itemStack)
                    {
                        return materialIn == Material.WOOD? 70:-1;
                    }
                }.setRegistryName(this.getRegistryName())
        );
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
