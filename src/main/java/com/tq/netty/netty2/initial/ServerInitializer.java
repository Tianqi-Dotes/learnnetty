package com.tq.netty.netty2.initial;

import com.tq.netty.netty2.handler.CustomHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpServerCodec;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline= socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new CustomHandler());
    }
}
