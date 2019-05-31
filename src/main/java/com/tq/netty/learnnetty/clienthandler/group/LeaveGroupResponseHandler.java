package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.LeaveGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LeaveGroupResponseHandler extends SimpleChannelInboundHandler<LeaveGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LeaveGroupResponsePacket msg) throws Exception {

        if(msg.isSusccess()) {
            System.out.println("server: you success left the group : " + msg.getGroupId());
        }else {
            System.out.println("server: leaving the group failed");
        }
    }
}
