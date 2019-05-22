package com.tq.netty.learnnetty.model.packets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;
import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {

    private String userId;
    private String username;
    private String password;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
