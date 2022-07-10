package com.momo.fd.network;

import com.momo.fd.MoMoFramework;
import com.momo.fd.network.protocols.PacketTest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(MoMoFramework.MODID);

    static int id = 0;
    public static void init()
    {
        //C2S
        channel.registerMessage(PacketTest.Handler.class, PacketTest.class, id++, Side.SERVER);
        //just call SendToServer


        //S2C
        //PacketUtil.network.sendTo(new PacketRevenge(cap.isRevengeActive()), (EntityPlayerMP)e.player);
    }

    public static void register()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(MoMoFramework.MODID, new GuiHandler());
    }

    public static void SendToServer(IMessage packet)
    {
        channel.sendToServer(packet);
    }
}
