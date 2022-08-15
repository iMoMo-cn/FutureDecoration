package com.momo.fd.network.message;

import com.momo.fd.feature.FeatureComposter;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageComposterAddLevel implements IMessage{

    public BlockPos pos;
    public int newLevel;
    public int level;

    public MessageComposterAddLevel(BlockPos pos, int newLevel, int level)
    {
        this.pos = pos;
        this.level = level;
        this.newLevel = newLevel;
    }

    public MessageComposterAddLevel()
    {
        //no op
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = BlockPos.fromLong(buf.readLong());
        level = buf.readInt();
        newLevel = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeLong(pos.toLong());
        buf.writeInt(level);
        buf.writeInt(newLevel);
    }

    public static class Handler implements IMessageHandler<MessageComposterAddLevel, IMessage>
    {

        @Override
        public IMessage onMessage(MessageComposterAddLevel message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                FeatureComposter.levelAdded(message.pos, message.newLevel, message.level);
            });
            return null;
        }
    }

}
