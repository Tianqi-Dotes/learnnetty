package com.tq.netty.learnnetty.model;

public interface Command {

    Byte LOGIN_REQUEST=1;
    Byte LOGIN_RESPONSE=2;
    Byte MESSAGE_REQUEST=3;
    Byte MESSAGE_RESPONSE=4;

}
