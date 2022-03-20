package com.momo.fd.item.armors;

import com.momo.fd.MoMoFramework;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemArmorBase extends ItemArmor implements IHasModel {
    public ItemArmorBase(String name, ItemArmor.ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, 0, equipmentSlotIn);
        setUnlocalizedName(name);
        setRegistryName(name);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(this, 0, "inventory"); }
}
