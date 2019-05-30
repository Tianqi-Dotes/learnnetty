package com.tq.netty.learnnetty.util;

import com.tq.netty.learnnetty.Session;
import com.tq.netty.learnnetty.attributes.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> sessionMap=new ConcurrentHashMap();


    public static void bindSession(Session session,Channel channel){
        sessionMap.put(session.getUserId(),channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel){
        if(hasLogin(channel)){
            sessionMap.remove(getSessionFromChannel(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);

        }
    }

    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSessionFromChannel(Channel c){
        return c.attr(Attributes.SESSION).get();
    }

    public static Channel getUserChannel(String userId){
        return sessionMap.get(userId);
    }
}
