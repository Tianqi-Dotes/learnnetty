package com.tq.netty.learnnetty.clienthandler.idle;

import com.tq.netty.learnnetty.model.packets.HeartBeatsPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class ClientHeartBeatsHandler extends ChannelInboundHandlerAdapter {
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    private void scheduledSendHeartBeats(ChannelHandlerContext context){
        context.executor().schedule(()->{
            if(context.channel().isActive()){
                context.writeAndFlush(new HeartBeatsPacket());
                scheduledSendHeartBeats(context);
            }
        },HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
