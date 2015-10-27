package com.toparchy.molecule.push.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {
	private ChannelInitializer<Channel> channelInitializer;

	private int port = 9999;

	private String host = "localhost";

	public NettyServer(int port) {
		this.port = port;
	}

	public void run() {
		EventLoopGroup acceptor = new NioEventLoopGroup(1);
		EventLoopGroup worker = new NioEventLoopGroup(RuntimeUtils.getProcessors() * 2);
		channelInitializer = new PushChannelInitializer();
		try {
			ServerBootstrap sbs = new ServerBootstrap();
			sbs.group(acceptor, worker).channel(NioServerSocketChannel.class)
					.childOption(ChannelOption.TCP_NODELAY, true)
					.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT).localAddress(host, port)
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(channelInitializer);
			sbs.bind().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
