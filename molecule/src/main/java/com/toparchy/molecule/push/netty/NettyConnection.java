package com.toparchy.molecule.push.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * TCP 连接
 * 
 * @author flatychen
 * 
 */
public class NettyConnection {

	private ChannelHandlerContext context;

	public NettyConnection(ChannelHandlerContext context) {
		super();
		this.context = context;
	}

	public ChannelFuture writeAndFlush(String s) {
		return context.writeAndFlush(s);
	}

}
