package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import sun.java2d.pipe.SpanShapeRenderer;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        if(msg.isSuccess()) {
            System.out.println("client recieved success group create msg, group id is " + msg.getGroupId());

            System.out.println("client receive group member msg from server, memebers: " + msg.getUserNameList());
        }else{

            System.out.println("create group failed");
        }
    }
}
