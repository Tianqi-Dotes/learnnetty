package com.tq.netty.learnnetty.netty;

import com.tq.netty.learnnetty.clienthandler.group.*;
import com.tq.netty.learnnetty.clienthandler.LoginResponseHandler;
import com.tq.netty.learnnetty.clienthandler.MessageResponseHandler;
import com.tq.netty.learnnetty.encode.PacketDecoder;
import com.tq.netty.learnnetty.encode.PacketEncoder;
import com.tq.netty.learnnetty.encode.Spliter;
import com.tq.netty.learnnetty.model.groupcommands.ConsoleCommandManager;
import com.tq.netty.learnnetty.model.groupcommands.LoginConsoleCommand;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GroupChatClient {

    private static final int MAX_RETRY=5;

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup worker=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());

                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new CheckGroupMembersResponseHandler());
                        ch.pipeline().addLast(new LeaveGroupResponseHandler());
                        ch.pipeline().addLast(new SendMsgResponseHandler());


                        ch.pipeline().addLast(new PacketEncoder());
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

        ConsoleCommandManager consoleCommandManager=new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand=new LoginConsoleCommand();
        Scanner scanner=new Scanner(System.in);

        new Thread(()->{
            while(!Thread.interrupted()){
                if(!SessionUtil.hasLogin(channel)){
                    loginConsoleCommand.exec(scanner,channel);
                }else {
                    consoleCommandManager.exec(scanner,channel);
                }
            }
        }).start();
    }

    private static void waitForLoginResponse(){
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
