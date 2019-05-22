package com.tq.netty.learnnetty.util;

import com.tq.netty.learnnetty.attributes.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;

public class LoginUtil {

    public static void markAsLogin(Channel channel){

        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> attributes=channel.attr(Attributes.LOGIN);

        return attributes.get()!=null;
    }
}
