package com.tq.netty.learnnetty.clienthandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class TestOutBoundHandler {

    public static class OutBoundHandlerA extends ChannelOutboundHandlerAdapter{
        @Override
        public void write(ChannelHandlerContext context, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OutA："+msg);
            super.write(context, msg, promise);
        }
    }
    public static class OutBoundHandlerB extends ChannelOutboundHandlerAdapter{
        @Override
        public void write(ChannelHandlerContext context, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OutB："+msg);
            super.write(context, msg, promise);
        }
    }
    public static class OutBoundHandlerC extends ChannelOutboundHandlerAdapter{
        @Override
        public void write(ChannelHandlerContext context, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("OutC："+msg);
            super.write(context, msg, promise);
        }
    }

}
