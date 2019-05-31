package com.tq.netty.learnnetty.model.groupcommands;

import com.tq.netty.learnnetty.model.packets.grouppackets.JoinGroupRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.LeaveGroupRequestPacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LeaveGroupCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {

        LeaveGroupRequestPacket packet=new LeaveGroupRequestPacket();
        System.out.println("please enter the group Id:");
        String groupId=scanner.next();

        String userId= SessionUtil.getSessionFromChannel(channel).getUserId();
        packet.setUserId(userId);
        packet.setGroupId(groupId);

        channel.writeAndFlush(packet);
    }
}
