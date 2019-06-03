package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.clienthandler.group.*;
import com.tq.netty.learnnetty.model.Packet;
import com.tq.netty.learnnetty.model.packets.grouppackets.SendMsgRequestPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import static com.tq.netty.learnnetty.model.Command.*;

import java.util.HashMap;
import java.util.Map;


@ChannelHandler.Sharable
public class ParallelHandlers extends SimpleChannelInboundHandler<Packet> {

    public static final ParallelHandlers singleton=new ParallelHandlers();

    private Map<Byte,SimpleChannelInboundHandler<? extends Packet>> handlerMap;
    private ParallelHandlers(){
        handlerMap=new HashMap<>();

        handlerMap.put(SEND_MSG_REQ, SendMsgRequestHandler.singleton);
        handlerMap.put(GROUP_CREATE_REQUEST, CreateGroupRequestHandler.singleton);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.singleton);
        handlerMap.put(LEAVE_GROUP_REQUEST, LeaveGroupRequestHandler.singleton);
        handlerMap.put(CHECK_GROUP_MEMBERS_REQ, CheckGroupMembersRequestHandler.singleton);
        handlerMap.put(SEND_MSG_REQ, SendMsgRequestHandler.singleton);
        handlerMap.put(LOGOUT_REQ, LogoutRequestHandler.singleton);


    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {

        handlerMap.get(msg.getCommand()).channelRead(ctx,msg);
    }
}
