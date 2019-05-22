package com.tq.netty.learnnetty.clienthandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg){

        ByteBuf byteBuf=(ByteBuf)msg;
        System.out.println(new Date()+" get msg :"+byteBuf.toString(Charset.forName("utf-8")));


        ByteBuf writeByteBuf=context.alloc().buffer();

        byte[] write=" sir tq, we have received your msg!".getBytes();
        writeByteBuf.writeBytes(write);
        context.channel().writeAndFlush(writeByteBuf);
    }

}
