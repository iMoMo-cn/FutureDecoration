package com.momo.fd.item.potions;

import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;

import javax.annotation.Nullable;

public class PotionTypeBase extends PotionType {
    public PotionTypeBase(@Nullable String name, PotionEffect... potionEffects)
    {
        super(name, potionEffects);

        ModPotionType.POTION_TYPES.add(this);
    }

    public static void register()
    {
//        PotionHelper.addMix(PotionTypes.AWKWARD, ModItems.MATERIALS_NAME, POTION_TYPE_NAME);
//        PotionHelper.addMix(POTION_TYPE_NAME, Items.REDSTONE, POTION_TYPE_LONG_NAME);
//        PotionHelper.addMix(POTION_TYPE_NAME, Items.GLOWSTONE_DUST, POTION_TYPE_STRONG_NAME);
//        PotionHelper.addMix(POTION_TYPE_STRONG_NAME, Items.REDSTONE, POTION_TYPE_LONG_STRONG_NAME);
//        PotionHelper.addMix(POTION_TYPE_LONG_NAME, Items.GLOWSTONE_DUST, POTION_TYPE_LONG_STRONG_NAME);
    }
}
