package com.tq.netty.learnnetty.model.packets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    private String msg;
    private String toUserId;


    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public MessageRequestPacket(String toUserId,String msg){
        this.toUserId=toUserId;
        this.msg=msg;
    }
}
