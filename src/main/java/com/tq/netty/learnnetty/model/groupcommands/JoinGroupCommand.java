package com.tq.netty.learnnetty.model.groupcommands;

import com.tq.netty.learnnetty.model.packets.grouppackets.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("please enter the group Id:");
        String groupId=scanner.next();

        JoinGroupRequestPacket packet=new JoinGroupRequestPacket();
        packet.setGroupId(groupId);
        channel.writeAndFlush(packet);
    }
}
