package com.toparchy.NettyPush.server.codec.push;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.toparchy.NettyPush.server.conn.NettyConnection;
import com.toparchy.NettyPush.services.ClientDispacherService;
import com.toparchy.NettyPush.utils.beanFactoryUtils;

/**
 * 
 * 业务处理.
 * 
 * @author flatychen
 * 
 */
public class DeserializeHandler extends SimpleChannelInboundHandler<String> {

	private ClientDispacherService deserialize;

	public DeserializeHandler() {
		super();
		deserialize = beanFactoryUtils.getClientDispacherService();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// 分发
		NettyConnection conn = new NettyConnection(ctx);
		deserialize.dispacher(conn, msg);
	}

}