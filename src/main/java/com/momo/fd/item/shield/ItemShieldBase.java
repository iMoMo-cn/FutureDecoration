package com.momo.fd.item.shield;

import com.momo.fd.MoMoFramework;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.*;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nullable;

public class ItemShieldBase extends ItemShield implements IHasModel {
    String displayName;
    public ItemShieldBase(String name, int maxDamageIn){
        super();
        setUnlocalizedName(name);
        setRegistryName(name);
        this.setMaxDamage(maxDamageIn);
        this.displayName = name;

        ModItems.ITEMS.add(this);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
            return I18n.translateToLocal("item." + displayName + ".name");
    }

    public boolean isShield(ItemStack stack, @Nullable EntityLivingBase entity)
    {
        return true;
    }

  @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
//        if(this == ModItems.NAME_SHIELD) {
//            return repair.getItem() == Items.MATERIALS;}
//        else
            return super.getIsRepairable(toRepair, repair);
    }

    @Override
    public void registerModels() {
        MoMoFramework.proxy.registerItemRenderer(this, 0, "inventory"); }
}
