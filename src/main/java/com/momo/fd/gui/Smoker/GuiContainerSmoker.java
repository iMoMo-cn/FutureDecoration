package com.momo.fd.gui.Smoker;

import com.momo.fd.blocks.blockTile.TileEntitySmoker;
import com.momo.fd.gui.GuiContainerBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiContainerSmoker extends GuiContainerBase {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");
    private TileEntitySmoker tileSmoker;

    public GuiContainerSmoker(EntityPlayer player, World world, int x, int y, int z)
    {
        super(new ContainerSmoker(player, world, x, y, z), TEXTURE, "tile.smoker.name");
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof TileEntitySmoker) {
            tileSmoker = (TileEntitySmoker)tileEntity;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String text = this.tileSmoker.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(text, this.xSize / 2 - this.fontRenderer.getStringWidth(text) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 3, 4210752);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        if (TileEntitySmoker.isBurning(tileSmoker))
        {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.getCookProgressScaled(24);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = tileSmoker.getField(2);
        int j = tileSmoker.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    private int getBurnLeftScaled(int pixels)
    {
        int i = tileSmoker.getField(1);

        if (i == 0)
        {
            i = 200;
        }

        return tileSmoker.getField(0) * pixels / i;
    }

}
