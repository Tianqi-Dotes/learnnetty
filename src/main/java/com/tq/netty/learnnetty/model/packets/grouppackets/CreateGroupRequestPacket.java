package com.tq.netty.learnnetty.model.packets.grouppackets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

import java.util.List;

@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String>  userIdList;

    @Override
    public Byte getCommand() {
        return Command.GROUP_CREATE_REQUEST;
    }
}
