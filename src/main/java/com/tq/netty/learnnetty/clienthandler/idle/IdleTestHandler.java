package com.tq.netty.learnnetty.clienthandler.idle;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class IdleTestHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME=15;


    public IdleTestHandler() {
        super(READER_IDLE_TIME, 0,0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {

        System.out.println(READER_IDLE_TIME+"秒内未读取到数据,关闭链接");
        ctx.channel().close();
    }
}
