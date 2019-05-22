package com.tq.netty.learnnetty.model.packets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    private String msg;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
