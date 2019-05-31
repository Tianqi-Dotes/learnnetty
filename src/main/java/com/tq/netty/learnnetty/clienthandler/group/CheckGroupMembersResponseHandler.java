package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.CheckGroupMembersRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.CheckGroupMembersResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class CheckGroupMembersResponseHandler extends SimpleChannelInboundHandler<CheckGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CheckGroupMembersResponsePacket msg) throws Exception {

        System.out.println("group "+msg.getGroupId()+" has the members : "+msg.getUserNames());
    }
}
