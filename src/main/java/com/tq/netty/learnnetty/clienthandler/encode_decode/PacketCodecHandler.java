package com.tq.netty.learnnetty.clienthandler.encode_decode;

import com.tq.netty.learnnetty.encode.PacketCodeC;
import com.tq.netty.learnnetty.model.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodecHandler singleton=new PacketCodecHandler();

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {

        ByteBuf buf= ctx.alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(buf,msg);
        out.add(buf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        out.add(PacketCodeC.INSTANCE.decode(msg));
    }
}
