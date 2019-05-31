package com.tq.netty.learnnetty.model.packets.grouppackets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class LeaveGroupRequestPacket extends Packet {


    private String groupId;
    private String userId;


    @Override
    public Byte getCommand() {
        return Command.LEAVE_GROUP_REQUEST;
    }
}
