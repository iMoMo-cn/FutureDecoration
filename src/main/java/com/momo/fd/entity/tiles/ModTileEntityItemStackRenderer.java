package com.momo.fd.entity.tiles;

import com.momo.fd.MoMoFramework;
import com.momo.fd.item.shield.ItemShieldBase;
import com.momo.fd.item.shield.ModelModShield;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.ResourceLocation;

public class ModTileEntityItemStackRenderer extends TileEntityItemStackRenderer {
    private final ModelModShield modelShield = new ModelModShield();
    private final TileEntityBanner banner = new TileEntityBanner();


    @Override
    public void renderByItem(ItemStack itemStackIn){
        Item item = itemStackIn.getItem();

        if (item instanceof ItemShieldBase)
        {
            if (itemStackIn.getSubCompound("BlockEntityTag") != null)
            {
                this.banner.setItemValues(itemStackIn, true);
                Minecraft.getMinecraft().getTextureManager().bindTexture(
                        BannerTextures.SHIELD_DESIGNS.getResourceLocation(this.banner.getPatternResourceLocation(), this.banner.getPatternList(), this.banner.getColorList()));
            }
            else
            {
                Minecraft.getMinecraft().getTextureManager().bindTexture(
                        new ResourceLocation(MoMoFramework.MODID + ":textures/entity/shield/" + item.getUnlocalizedName()+ "_base_nopattern.png"));
            }

            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0F, -1.0F, -1.0F);
            this.modelShield.render();
            GlStateManager.popMatrix();
        }
    }
}
