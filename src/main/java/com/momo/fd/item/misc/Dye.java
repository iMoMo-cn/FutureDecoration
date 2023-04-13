package com.momo.fd.item.misc;

import com.momo.fd.MoMoFramework;
import com.momo.fd.item.ModItems;
import com.momo.fd.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class Dye extends Item implements IHasModel {

    public EnumDyeColor dyeColor;

    public Dye(String name, EnumDyeColor color)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MISC);

        this.dyeColor = color;

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels()
    {
        MoMoFramework.proxy.registerItemRenderer(this, 0, "inventory");
    }

    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
        EnumDyeColor enumdyecolor = this.dyeColor;

        if (target instanceof EntitySheep)
        {
            EntitySheep entitysheep = (EntitySheep)target;

            if (!entitysheep.getSheared() && entitysheep.getFleeceColor() != enumdyecolor)
            {
                entitysheep.setFleeceColor(enumdyecolor);
                if (!playerIn.capabilities.isCreativeMode)
                {
                    stack.shrink(1);
                }
            }

            return true;
        }
        else if (target instanceof EntityWolf)
        {
            EntityWolf entitywolf = (EntityWolf)target;

            if (enumdyecolor != entitywolf.getCollarColor())
            {
                entitywolf.setCollarColor(enumdyecolor);

                if (!playerIn.capabilities.isCreativeMode)
                {
                    stack.shrink(1);
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
}
