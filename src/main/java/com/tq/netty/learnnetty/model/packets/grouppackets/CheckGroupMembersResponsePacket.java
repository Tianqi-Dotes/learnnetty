package com.tq.netty.learnnetty.model.packets.grouppackets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

import java.util.List;

@Data
public class CheckGroupMembersResponsePacket extends Packet {

    private String groupId;
    private List<String> userNames;
    @Override
    public Byte getCommand() {
        return Command.CHECK_GROUP_MEMBERS_RES;
    }
}
