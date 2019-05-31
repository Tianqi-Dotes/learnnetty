package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.CreateGroupRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.CreateGroupResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIds=msg.getUserIdList();

        List<String> userNameList=new ArrayList<>();

        ChannelGroup channelGroup=new DefaultChannelGroup(ctx.executor());

        for(String userId:userIds){
            Channel channel= SessionUtil.getUserChannel(userId);
            if(channel!=null){
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSessionFromChannel(channel).getUserName());
            }
        }

        CreateGroupResponsePacket packet=new CreateGroupResponsePacket();
        packet.setSuccess(true);
        String groupId=UUID.randomUUID().toString().substring(0,8);
        packet.setGroupId(groupId);
        packet.setUserNameList(userNameList);

        System.out.println("group create successfully! group id is "+groupId);
        System.out.println("members in the group is :"+userNameList);


        SessionUtil.setChannelGroupById(groupId,channelGroup);
        channelGroup.writeAndFlush(packet);//次数 group 设定了发给哪个客户端

    }
}
