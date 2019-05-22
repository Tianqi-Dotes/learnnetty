package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.encode.PacketCodeC;
import com.tq.netty.learnnetty.model.packets.LoginRequestPacket;
import com.tq.netty.learnnetty.model.packets.LoginResponsePacket;
import com.tq.netty.learnnetty.model.Packet;
import com.tq.netty.learnnetty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext context){
        System.out.println(new Date()+"--客户登陆");

        LoginRequestPacket packet=new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setUsername("tq");
        packet.setPassword("gg");

        ByteBuf buf= PacketCodeC.INSTANCE.encode(context.alloc(),packet);
        context.channel().writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg){

        ByteBuf byteBuf=(ByteBuf) msg;

        Packet packet=PacketCodeC.INSTANCE.decode(byteBuf);
        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket=(LoginResponsePacket) packet;

            if(loginResponsePacket.isSuccess()){
                LoginUtil.markAsLogin(context.channel());
                System.out.println(new Date()+"-收到服务器校验信息---客户端登陆成功");
            }else {
                System.out.println(new Date()+"-收到服务器校验信息---客户端登陆失败");
            }
        }
    }

}
