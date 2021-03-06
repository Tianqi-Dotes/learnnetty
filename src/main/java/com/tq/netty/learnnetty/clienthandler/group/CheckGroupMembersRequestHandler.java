package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.CheckGroupMembersRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.CheckGroupMembersResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class CheckGroupMembersRequestHandler extends SimpleChannelInboundHandler<CheckGroupMembersRequestPacket> {

    public static final CheckGroupMembersRequestHandler singleton=new CheckGroupMembersRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CheckGroupMembersRequestPacket msg) throws Exception {

        System.out.println("received client check members command!! groupId is "+msg.getGroupId());
        String groupId=msg.getGroupId();
        ChannelGroup channelGroup= SessionUtil.getChannelGroupByGroupId(groupId);

        List<String> userList=new ArrayList<>();
        for(Channel channel:channelGroup){
            userList.add(SessionUtil.getSessionFromChannel(channel).getUserName());
        }

        CheckGroupMembersResponsePacket packet=new CheckGroupMembersResponsePacket();
        packet.setUserNames(userList);
        packet.setGroupId(groupId);

        System.out.println("send members response to client with members : "+packet.getUserNames());
        ctx.channel().writeAndFlush(packet);
    }
}
