package com.tq.netty.learnnetty.model.packets.grouppackets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class SendMsgRequestPacket extends Packet {


    private String groupId;
    private String msg;


    @Override
    public Byte getCommand() {
        return Command.SEND_MSG_REQ;
    }
}
