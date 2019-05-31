package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.JoinGroupRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.JoinGroupResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {

        if(msg.isSuccess()){
            System.out.println("server: you have success entered the group: "+msg.getGroupId());
        }else{
            System.out.println("server :failed enter the group :" +msg.getGroupId());
        }
    }
}
