package com.momo.fd.gui.Barrel;

import com.momo.fd.MoMoFramework;
import com.momo.fd.gui.GuiContainerBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiContainerBarrel extends GuiContainerBase {

    public GuiContainerBarrel(EntityPlayer player, World world, int x, int y, int z)
    {
        super(new ContainerBarrel(player, world, x, y, z),
                new ResourceLocation(MoMoFramework.MODID + ":textures/gui/container/generic_27.png"),
                    "tile.barrel.name");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String text = I18n.format("tile.barrel.name");
        this.fontRenderer.drawString(text, 8, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 3, 4210752);
    }
}
