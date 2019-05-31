package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.JoinGroupRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.JoinGroupResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {

        String groupId=msg.getGroupId();
        ChannelGroup channelGroup= SessionUtil.getChannelGroupByGroupId(groupId);

        channelGroup.add(ctx.channel());


        JoinGroupResponsePacket packet=new JoinGroupResponsePacket();
        packet.setGroupId(groupId);
        packet.setSuccess(true);

        System.out.println("server: process Join request and response to the client");

        ctx.channel().writeAndFlush(packet);
    }
}
