package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.SendMsgRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.SendMsgResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class SendMsgResponseHandler extends SimpleChannelInboundHandler<SendMsgResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendMsgResponsePacket msg) throws Exception {

        System.out.println("client received msg from group :"+msg.getGroupId()+" and the msg is "+msg.getMsg());
    }
}
