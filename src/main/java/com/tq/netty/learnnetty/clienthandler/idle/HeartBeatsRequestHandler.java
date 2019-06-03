package com.tq.netty.learnnetty.clienthandler.idle;

import com.tq.netty.learnnetty.model.packets.HeartBeatsPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class HeartBeatsRequestHandler extends SimpleChannelInboundHandler<HeartBeatsPacket> {

    public static final HeartBeatsRequestHandler singleton=new HeartBeatsRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatsPacket msg) throws Exception {
        ctx.writeAndFlush(msg);
    }
}
