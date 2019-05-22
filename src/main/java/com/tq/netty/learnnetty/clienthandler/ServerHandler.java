package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.encode.PacketCodeC;
import com.tq.netty.learnnetty.model.packets.LoginRequestPacket;
import com.tq.netty.learnnetty.model.packets.LoginResponsePacket;
import com.tq.netty.learnnetty.model.Packet;
import com.tq.netty.learnnetty.model.packets.MessageRequestPacket;
import com.tq.netty.learnnetty.model.packets.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg){
        System.out.println("处理客户端登陆------");
        ByteBuf byteBuf=(ByteBuf) msg;

        Packet packet= PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket=(LoginRequestPacket) packet;


            LoginResponsePacket responsePacket=new LoginResponsePacket();
            responsePacket.setVersion(packet.getVersion());


            if(valid(loginRequestPacket)){
                responsePacket.setSuccess(true);
            }else{
                responsePacket.setReason("登陆校验失败");
                responsePacket.setSuccess(false);
            }
            ByteBuf response=PacketCodeC.INSTANCE.encode(context.alloc(),responsePacket);


            System.out.println("服务器向客户端响应------");
            context.channel().writeAndFlush(response);
        }else if(packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket=(MessageRequestPacket)packet;
            System.out.println(new Date()+"--服务器接受客户端消息"+messageRequestPacket.getMsg());

            MessageResponsePacket responsePacket=new MessageResponsePacket();
            responsePacket.setMsg("服务器已经收到客户端["+messageRequestPacket.getMsg()+"]");
            ByteBuf responseBuf=PacketCodeC.INSTANCE.encode(context.alloc(),responsePacket);
            context.channel().writeAndFlush(responseBuf);
        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}
