package com.tq.netty.learnnetty.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TestInBoundHandler {


    public static class InBoundHandlerA extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext context,Object msg) throws Exception {
            System.out.println("A:"+msg);
            super.channelRead(context,msg);
        }
    }
    public static class InBoundHandlerB extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext context,Object msg) throws Exception {
            System.out.println("B:"+msg);
            super.channelRead(context,msg);
        }
    }
    public static class InBoundHandlerC extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext context,Object msg) throws Exception {
            System.out.println("C:"+msg);
            super.channelRead(context,msg);
        }
    }
}
