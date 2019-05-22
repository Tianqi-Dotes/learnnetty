package com.tq.netty.learnnetty.netty;

import com.tq.netty.learnnetty.clienthandler.ClientHandler;
import com.tq.netty.learnnetty.clienthandler.FirstClientHandler;
import com.tq.netty.learnnetty.encode.PacketCodeC;
import com.tq.netty.learnnetty.model.packets.MessageRequestPacket;
import com.tq.netty.learnnetty.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {

    private static final int MAX_RETRY=5;

    public static void main(String[] args) throws InterruptedException {
        /*Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }*/
        NioEventLoopGroup worker=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap,"127.0.0.1",8081,MAX_RETRY);
    }
    private static void connect(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("success");
                Channel channel=((ChannelFuture)future).channel();
                startConsoleThread(channel);
            }else if(retry==0){
                System.out.println("no retry count");
            }else {
                int current=(MAX_RETRY-retry)+1;
                int delay=1<<current;

                System.out.println("the "+current+" times retry....");
                bootstrap.config().group().schedule(()->connect(bootstrap,host,port,retry-1),delay, TimeUnit.SECONDS);
                //connect(bootstrap,host,port);
            }
        });
    }


    private static void startConsoleThread(Channel channel){
        new Thread(()->{
            while(!Thread.interrupted()){
                if(LoginUtil.hasLogin(channel)){
                    System.out.println("请输入msg发送至服务器:");
                    Scanner scanner=new Scanner(System.in);
                    String line=scanner.nextLine();

                    MessageRequestPacket packet=new MessageRequestPacket();
                    packet.setMsg(line);
                    ByteBuf byteBuf= PacketCodeC.INSTANCE.encode(channel.alloc(),packet);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }

}
