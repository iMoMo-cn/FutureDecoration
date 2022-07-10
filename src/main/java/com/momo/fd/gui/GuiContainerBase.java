package com.momo.fd.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiContainerBase extends GuiContainer
{
    private ResourceLocation BG_TEXTURE;
    private String text;

    public GuiContainerBase(Container container, ResourceLocation texture, String textIn)
    {
        super(container);
        BG_TEXTURE = texture;
        text = I18n.format(textIn);
        xSize = 176;
        ySize = 166;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        super.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        // set to white
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        // bind texture so that when rect drawn it draws with this texture
        this.mc.getTextureManager().bindTexture(BG_TEXTURE);

        // centre on the screen
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        // draw the rect with the texture
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(text, this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
}
