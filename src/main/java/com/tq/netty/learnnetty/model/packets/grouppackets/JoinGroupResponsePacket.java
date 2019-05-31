package com.tq.netty.learnnetty.model.packets.grouppackets;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class JoinGroupResponsePacket extends Packet {

    private boolean isSuccess;
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
