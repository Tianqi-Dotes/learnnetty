package com.tq.netty.learnnetty.model.groupcommands;

import com.tq.netty.learnnetty.model.packets.grouppackets.CheckGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class GroupStatusCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {


        System.out.println("type in the group id you want see the details!");
        String groupId=scanner.next();
        CheckGroupMembersRequestPacket packet=new CheckGroupMembersRequestPacket();
        packet.setGroupId(groupId);

        channel.writeAndFlush(packet);
    }
}
