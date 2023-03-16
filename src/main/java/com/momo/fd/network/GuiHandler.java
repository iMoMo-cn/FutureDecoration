package com.momo.fd.network;

import com.momo.fd.gui.Barrel.ContainerBarrel;
import com.momo.fd.gui.Barrel.GuiContainerBarrel;
import com.momo.fd.gui.BlastFurnace.ContainerBlastFurnace;
import com.momo.fd.gui.BlastFurnace.GuiContainerBlastFurnace;
import com.momo.fd.gui.Smoker.ContainerSmoker;
import com.momo.fd.gui.Smoker.GuiContainerSmoker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int BARREL = 1;
    public static final int SMOKER = 2;
    public static final int BLAST_FURNACE = 3;

    @Override
    public Container getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
        switch (id) {
            case BARREL:
                return new ContainerBarrel(player, world, x, y, z);
            case SMOKER:
                return new ContainerSmoker(player, world, x, y, z);
            case BLAST_FURNACE:
                return new ContainerBlastFurnace(player, world, x, y, z);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z){
        switch (id) {
            case BARREL:
                return new GuiContainerBarrel(player, world, x, y, z);
            case SMOKER:
                return new GuiContainerSmoker(player, world, x, y, z);
            case BLAST_FURNACE:
                return new GuiContainerBlastFurnace(player, world, x, y, z);
            default:
                return null;
        }
    }
}
