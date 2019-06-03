package com.tq.netty.learnnetty.model;

public interface Command {

    Byte LOGIN_REQUEST=1;
    Byte LOGIN_RESPONSE=2;
    Byte MESSAGE_REQUEST=3;
    Byte MESSAGE_RESPONSE=4;

    Byte GROUP_CREATE_REQUEST=5;
    Byte GROUP_CREATE_RESPONSE=6;

    Byte JOIN_GROUP_REQUEST=7;
    Byte JOIN_GROUP_RESPONSE=8;

    Byte LEAVE_GROUP_REQUEST=9;
    Byte LEAVE_GROUP_RESPONSE=10;

    Byte CHECK_GROUP_MEMBERS_REQ=11;
    Byte CHECK_GROUP_MEMBERS_RES=12;

    Byte SEND_MSG_REQ=13;
    Byte SEND_MSG_RES=14;

    Byte LOGOUT_REQ=15;
    Byte LOGOUT_RES=16;

    Byte HEART_BEATS=17;
}
