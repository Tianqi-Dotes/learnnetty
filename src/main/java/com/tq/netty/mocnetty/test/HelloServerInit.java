package com.tq.netty.mocnetty.test;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HelloServerInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline= ch.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new CustomeHandler());
    }
}
