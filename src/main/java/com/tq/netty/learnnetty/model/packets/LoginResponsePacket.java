package com.tq.netty.learnnetty.model.packets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String reason;
    private String userId;
    private String userName;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
