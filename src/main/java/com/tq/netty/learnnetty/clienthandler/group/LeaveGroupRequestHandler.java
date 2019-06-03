package com.tq.netty.learnnetty.clienthandler.group;

import com.tq.netty.learnnetty.model.packets.grouppackets.LeaveGroupRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.LeaveGroupResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class LeaveGroupRequestHandler extends SimpleChannelInboundHandler<LeaveGroupRequestPacket> {

    public static final LeaveGroupRequestHandler singleton=new LeaveGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LeaveGroupRequestPacket msg) throws Exception {
        String groupId=msg.getGroupId();
        ChannelGroup channelGroup= SessionUtil.getChannelGroupByGroupId(groupId);
        channelGroup.remove(ctx.channel());

        LeaveGroupResponsePacket packet=new LeaveGroupResponsePacket();
        packet.setSusccess(true);
        packet.setGroupId(groupId);

        ctx.channel().writeAndFlush(packet);
    }
}
