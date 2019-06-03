package com.tq.netty.learnnetty.model.packets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class LogoutResponsePacket extends Packet {

    private String userId;
    private String username;


    @Override
    public Byte getCommand() {
        return Command.LOGOUT_RES;
    }
}
