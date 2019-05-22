package com.tq.netty.learnnetty.model;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
public abstract class Packet {

    private Byte version=1;

    public abstract Byte getCommand();
}
