package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.Session;
import com.tq.netty.learnnetty.encode.PacketCodeC;
import com.tq.netty.learnnetty.model.packets.LoginRequestPacket;
import com.tq.netty.learnnetty.model.packets.LoginResponsePacket;
import com.tq.netty.learnnetty.util.LoginUtil;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket requestPacket) throws Exception {
        System.out.println("服务器收到客户端登陆请求------");

        LoginResponsePacket packet=login(requestPacket);
        //ByteBuf response= PacketCodeC.INSTANCE.encode(ctx.alloc(),packet);

        String userId= UUID.randomUUID().toString();
        System.out.println("your userid is : "+userId);
        packet.setUserId(userId);
        packet.setUserName(requestPacket.getUsername());
        SessionUtil.bindSession(new Session(userId,requestPacket.getUsername()),ctx.channel());
        System.out.println("服务器向客户端响应------");

        ctx.channel().writeAndFlush(packet);
    }

    @Override
    public void channelInactive(ChannelHandlerContext context){

        SessionUtil.unbindSession(context.channel());
    }

    private LoginResponsePacket login(LoginRequestPacket loginRequestPacket){
        LoginResponsePacket packet=new LoginResponsePacket();
        packet.setVersion(loginRequestPacket.getVersion());
        if(valid(loginRequestPacket)){
            packet.setSuccess(true);
            System.out.println(new Date()+":登陆成功");
        }else{
            packet.setReason("登陆校验失败");
            packet.setSuccess(false);
        }
        return packet;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}
