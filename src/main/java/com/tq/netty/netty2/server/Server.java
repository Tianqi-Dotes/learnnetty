package com.tq.netty.netty2.server;

import com.tq.netty.netty2.initial.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class Server {
    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());
                    /*.childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpServerCodec());
                            socketChannel.pipeline().addLast(new CustomHandler());
                        }
                    });
*/
            ChannelFuture future= bootstrap.bind(8444).sync();

            future.channel().closeFuture().sync();
            //ChannelFuture future = bootstrap.bind(8088).sync();

            //future.channel().closeFuture().sync();
            //bind(bootstrap,8844);
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {

                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }

}
