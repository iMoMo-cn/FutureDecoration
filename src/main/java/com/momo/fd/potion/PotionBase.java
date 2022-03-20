package com.momo.fd.potion;

import com.momo.fd.MoMoFramework;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class PotionBase extends Potion{
    protected final int iconIndex;

    public PotionBase(String name, boolean isBadEffect, int color, int icon) {
        super(isBadEffect, color);
        this.setRegistryName(name);
        this.setPotionName("effect." + name);

        iconIndex = icon;

        ModPotion.POTIONS.add(this);
    }

    public boolean hasEffect(EntityLivingBase entity) {
        return hasEffect(entity, this);
    }

    public boolean hasEffect(EntityLivingBase entity, Potion potion) {
        return entity.getActivePotionEffect(potion) != null;
    }

    //continuousPotion Time trigger
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    //potion effect
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health)
    {
//        if(this == ModPotion.NEW_POTION)
//        {
//              *addEffect
//        }
//
//        else if(this == ModPotion.PURIFY)
//        {
//              *addEffect
//        }
    }


    //tipped arrow effect
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
//        if(this == ModPotion.NEW_POTION)
//        {
//              *addEffect
//        }
//
//        else if(this == ModPotion.PURIFY)
//        {
//              *addEffect
//        }
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation(MoMoFramework.MODID + ":textures/gui/potion.png");

    @SideOnly(Side.CLIENT)
    protected void render(int x, int y, float alpha) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buf = tessellator.getBuffer();
        buf.begin(7, DefaultVertexFormats.POSITION_TEX);
        GlStateManager.color(1, 1, 1, alpha);

        int textureX = iconIndex % 14 * 18;
        int textureY = 198 - iconIndex / 14 * 18;

        buf.pos(x, y + 18, 0).tex(textureX * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
        buf.pos(x + 18, y + 18, 0).tex((textureX + 18) * 0.00390625, (textureY + 18) * 0.00390625).endVertex();
        buf.pos(x + 18, y, 0).tex((textureX + 18) * 0.00390625, textureY * 0.00390625).endVertex();
        buf.pos(x, y, 0).tex(textureX * 0.00390625, textureY * 0.00390625).endVertex();

        tessellator.draw();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect e, Minecraft mc){ render(x + 6, y + 7, 1); }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect e, Minecraft mc, float alpha){ render(x + 3, y + 3, alpha); }
}
