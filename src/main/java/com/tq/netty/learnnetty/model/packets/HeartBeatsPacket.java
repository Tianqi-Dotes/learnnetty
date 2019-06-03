package com.tq.netty.learnnetty.model.packets;

import com.tq.netty.learnnetty.model.Command;
import com.tq.netty.learnnetty.model.Packet;

public class HeartBeatsPacket extends Packet {


    @Override
    public Byte getCommand() {
        return Command.HEART_BEATS;
    }
}
