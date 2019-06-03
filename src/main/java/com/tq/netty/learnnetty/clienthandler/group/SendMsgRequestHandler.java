package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.JoinGroupRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.JoinGroupResponsePacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.SendMsgRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.SendMsgResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class SendMsgRequestHandler extends SimpleChannelInboundHandler<SendMsgRequestPacket> {

    public static final SendMsgRequestHandler singleton=new SendMsgRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendMsgRequestPacket msg) throws Exception {

        System.out.println("server start to process msgs to the group :"+msg.getGroupId()+" and the msg is : "+ msg.getMsg());
        String groupId=msg.getGroupId();
        ChannelGroup group=SessionUtil.getChannelGroupByGroupId(groupId);


        SendMsgResponsePacket packet=new SendMsgResponsePacket();
        packet.setGroupId(groupId);
        packet.setMsg(msg.getMsg());
        group.writeAndFlush(packet);

    }
}
