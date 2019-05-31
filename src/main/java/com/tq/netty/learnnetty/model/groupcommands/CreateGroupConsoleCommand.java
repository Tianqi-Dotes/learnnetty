package com.tq.netty.learnnetty.model.groupcommands;


import com.tq.netty.learnnetty.model.packets.grouppackets.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER=",";

    @Override
    public void exec(Scanner scanner, Channel channel) {

        CreateGroupRequestPacket packet=new CreateGroupRequestPacket();

        System.out.println("gather a new group! please enter userIds and seperate with ,comma:");
        String userIds=scanner.next();
        packet.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));


        channel.writeAndFlush(packet);

    }
}
