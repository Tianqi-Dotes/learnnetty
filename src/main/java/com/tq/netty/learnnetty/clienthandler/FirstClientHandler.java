package com.tq.netty.learnnetty.clienthandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext context){
        System.out.println(new Date()+":client write data");

        ByteBuf buffer=getByteBuf(context);
        context.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext context,Object o){
        ByteBuf read=(ByteBuf)o;

        System.out.println(new Date()+" we have received the msg replyed from server: "+read.toString(Charset.forName("utf-8")));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext context){
        ByteBuf buffer=context.alloc().buffer();

        byte[] dataBytes="nice to meet tq,hope you have a nice day".getBytes();

        buffer.writeBytes(dataBytes);
        return buffer;
    }
}
