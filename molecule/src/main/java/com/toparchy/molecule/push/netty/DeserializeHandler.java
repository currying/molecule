package com.toparchy.molecule.push.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class DeserializeHandler extends SimpleChannelInboundHandler<String> {
	private ClientDispacherService clientDispacherService = new ClientDispacherService();

	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// 分发
		NettyConnection conn = new NettyConnection(ctx);
		clientDispacherService.dispacher(conn, msg);
	}
}