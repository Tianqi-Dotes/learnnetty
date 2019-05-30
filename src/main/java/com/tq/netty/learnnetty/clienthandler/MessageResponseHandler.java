package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.model.packets.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        //System.out.println(new Date()+"收到服务器的消息:"+msg.getMsg());

        String fromUserId=msg.getFromUserId();
        String fromUserName=msg.getFromUserName();

        System.out.println("("+fromUserId+") "+fromUserName+" --> "+msg.getMsg());
    }

}
