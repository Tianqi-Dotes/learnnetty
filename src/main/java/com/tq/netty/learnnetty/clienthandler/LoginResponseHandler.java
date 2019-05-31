package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.Session;
import com.tq.netty.learnnetty.attributes.Attributes;
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

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    /*@Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }*/

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        System.out.println("客户端收到服务器登陆响应------");

        if(msg.isSuccess()){
            System.out.println("客户端收到服务器消息=----登陆成功");
            System.out.println("your user id is "+msg.getUserId());

            SessionUtil.bindSession(new Session(msg.getUserId(),msg.getUserName()),ctx.channel());

           // LoginUtil.markAsLogin(ctx.channel());
        }else{
            System.out.println(new Date() + ": 客户端登录失败，原因：" + msg.getReason());
        }
    }

}
