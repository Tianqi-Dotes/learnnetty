package com.tq.netty.learnnetty.clienthandler;

import com.tq.netty.learnnetty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(LoginUtil.hasLogin(ctx.channel())){
            ctx.pipeline().remove(this);//登陆成功后 以后不再需要auth handler
            System.out.println("移除登陆handler");
            super.channelRead(ctx,msg);
        }else{
            System.out.println("channel closed!no login info");
            ctx.channel().close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(LoginUtil.hasLogin(ctx.channel())){
            System.out.println("登陆成功后 以后不再需要auth handler");
        }else{
            System.out.println("haven't login but handler removed");
        }
    }
}
