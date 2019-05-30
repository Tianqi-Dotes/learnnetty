package com.tq.netty.learnnetty.attributes;

import com.tq.netty.learnnetty.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Boolean> LOGIN=AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION=AttributeKey.newInstance("session");

}
