package com.momo.fd.item.armors;

import com.momo.fd.item.ItemBase;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.item.ItemStack;

public class ItemHorseArmor extends ItemBase {
    private String armorName;

    public ItemHorseArmor(String name){
        super(name);
        this.setMaxStackSize(1);
        armorName = name;
    }

    @Override
    public HorseArmorType getHorseArmorType(ItemStack stack) {
//        if (this.armorName.equals("name_horse_armor")) {
//            return ModHorseArmor.HORSE_ARMOR_NAME; }
//        else
            return HorseArmorType.NONE;
    }
}
