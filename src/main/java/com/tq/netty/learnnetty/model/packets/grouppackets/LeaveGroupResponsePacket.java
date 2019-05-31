package com.tq.netty.learnnetty.model.packets.grouppackets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class LeaveGroupResponsePacket extends Packet {

    private boolean isSusccess;
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LEAVE_GROUP_RESPONSE;
    }
}
