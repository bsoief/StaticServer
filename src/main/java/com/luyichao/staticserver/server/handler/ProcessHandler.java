package com.luyichao.staticserver.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

public class ProcessHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {


    }
}
