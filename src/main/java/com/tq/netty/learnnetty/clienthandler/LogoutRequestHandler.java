package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.model.packets.LogoutRequestPacket;
import com.tq.netty.learnnetty.model.packets.LogoutResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler singleton=new LogoutRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {

        System.out.println("Server processing the logout account: "+msg.getUserId());
        SessionUtil.unbindSession(ctx.channel());

        LogoutResponsePacket packet=new LogoutResponsePacket();
        packet.setUserId(msg.getUserId());
        packet.setUsername(msg.getUsername());


        ctx.channel().writeAndFlush(packet);
    }
}
