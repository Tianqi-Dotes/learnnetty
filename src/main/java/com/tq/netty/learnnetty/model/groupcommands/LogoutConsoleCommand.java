package com.tq.netty.learnnetty.model.groupcommands;

import com.tq.netty.learnnetty.Session;
import com.tq.netty.learnnetty.model.packets.LogoutRequestPacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("are you sure to log out? y/n");
        if(scanner.next().equals("y")){

            LogoutRequestPacket packet=new LogoutRequestPacket();
            packet.setUserId(SessionUtil.getSessionFromChannel(channel).getUserId());
            packet.setUsername(SessionUtil.getSessionFromChannel(channel).getUserName());
            channel.writeAndFlush(packet);
        }else{

            System.out.println("no process is done");
        }
    }
}
