package com.momo.fd.item.bows;

import com.momo.fd.MoMoFramework;
import com.momo.fd.util.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ItemFastBow extends ItemBowBase implements IHasModel {
    public ItemFastBow(String name, int maxDamageIn, float delayIn) {
        super(name, maxDamageIn);

        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entityIn.getActiveItemStack().getItem() instanceof ItemFastBow ?
                            (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / delayIn : 0.0F;
                }
            }
        });
    }

    @Override
    public void registerModels()
    {
        MoMoFramework.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
