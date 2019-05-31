package com.tq.netty.learnnetty.model.groupcommands;

import com.tq.netty.learnnetty.model.packets.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        LoginRequestPacket loginRequestPacket=new LoginRequestPacket();
        System.out.println("enter user name:");
        loginRequestPacket.setUsername(scanner.nextLine());
        loginRequestPacket.setPassword("ggg");

        channel.writeAndFlush(loginRequestPacket);

        waitForLoginResponse();
    }

    private static void waitForLoginResponse(){
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
