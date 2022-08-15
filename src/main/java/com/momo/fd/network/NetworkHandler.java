package com.momo.fd.network;

import com.momo.fd.MoMoFramework;
import com.momo.fd.network.message.MessageComposterAddLevel;
import com.momo.fd.network.protocols.PacketTest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

    public static final SimpleNetworkWrapper channel = NetworkRegistry.INSTANCE.newSimpleChannel(MoMoFramework.MODID);

    static int nextID = 0;
    public static void init()
    {
        //C2S
        channel.registerMessage(PacketTest.Handler.class, PacketTest.class, nextID++, Side.SERVER);
        //just call SendToServer


        //S2C
        //PacketUtil.network.sendTo(new PacketRevenge(cap.isRevengeActive()), (EntityPlayerMP)e.player);
        channel.registerMessage(MessageComposterAddLevel.Handler.class, MessageComposterAddLevel.class, nextID++, Side.CLIENT);
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
