package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.Session;
import com.tq.netty.learnnetty.encode.PacketCodeC;
import com.tq.netty.learnnetty.model.packets.MessageRequestPacket;
import com.tq.netty.learnnetty.model.packets.MessageResponsePacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
       // MessageResponsePacket packet=processMessage(msg);
        //ByteBuf responseBuf= PacketCodeC.INSTANCE.encode(ctx.alloc(),packet);
        //ctx.channel().writeAndFlush(responseBuf);

       // System.out.println("服务器收到客户端消息："+msg.getMsg());
      //  packet.setMsg("服务器回复客户端消息"+msg.getMsg());
        //ctx.channel().writeAndFlush(packet);


        Session session= SessionUtil.getSessionFromChannel(ctx.channel());

        MessageResponsePacket packet1=new MessageResponsePacket();
        packet1.setFromUserId(session.getUserId());
        packet1.setFromUserName(session.getUserName());
        packet1.setMsg(msg.getMsg());

        Channel toChannel= SessionUtil.getUserChannel(msg.getToUserId());
        if(toChannel!=null&&SessionUtil.hasLogin(ctx.channel())){
            toChannel.writeAndFlush(packet1);
        }else{
            System.out.println("receiver currently offlane!");
        }
    }

    private MessageResponsePacket processMessage(MessageRequestPacket requestPacket){
        System.out.println(new Date()+"--服务器接受客户端消息"+requestPacket.getMsg());

        MessageResponsePacket responsePacket=new MessageResponsePacket();
        responsePacket.setMsg("服务器已经收到客户端["+requestPacket.getMsg()+"]");
        return responsePacket;
    }
}
