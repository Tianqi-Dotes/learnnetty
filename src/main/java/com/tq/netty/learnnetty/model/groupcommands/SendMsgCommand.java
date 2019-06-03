package com.tq.netty.learnnetty.model.groupcommands;

import com.tq.netty.learnnetty.model.packets.grouppackets.LeaveGroupRequestPacket;
import com.tq.netty.learnnetty.model.packets.grouppackets.SendMsgRequestPacket;
import com.tq.netty.learnnetty.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendMsgCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.println("please enter the group Id and the msg, seperate by '|':");
        String line=scanner.next();

        SendMsgRequestPacket packet=new SendMsgRequestPacket();
        packet.setGroupId(line.substring(0,line.indexOf("|")));
        packet.setMsg(line.substring(line.indexOf("|")+1,line.length()));

        System.out.println("group Id is "+packet.getGroupId());
        channel.writeAndFlush(packet);
    }
}
