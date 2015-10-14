//package com.toparchy.serial;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
//import io.netty.handler.codec.LengthFieldPrepender;
//import io.netty.handler.codec.serialization.ClassResolvers;
//import io.netty.handler.codec.serialization.ObjectDecoder;
//import io.netty.handler.codec.serialization.ObjectEncoder;
//
//public class EchoClient {
//	private final String host;
//	private final int port;
//	private final int sendNumber;
//
//	public EchoClient(String host, int port, int sendNumber) {
//		this.host = host;
//		this.port = port;
//		this.sendNumber = sendNumber;
//	}
//
//	public void run() {
//		EventLoopGroup group = new NioEventLoopGroup();
//		try {
//			Bootstrap b = new Bootstrap();
//			b.group(group).channel(NioSocketChannel.class)
//					.option(ChannelOption.TCP_NODELAY, true)
//					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
//					.handler(new ChannelInitializer<SocketChannel>() {
//						@Override
//						protected void initChannel(SocketChannel ch)
//								throws Exception {
//							ch.pipeline().addLast(
//									"frameDecoder",
//									new LengthFieldBasedFrameDecoder(65535, 0,
//											2, 0, 2));
//							ch.pipeline().addLast(
//									"msgpack decoder",
//									new ObjectDecoder(1024, ClassResolvers
//											.cacheDisabled(this.getClass()
//													.getClassLoader())));
//							ch.pipeline().addLast("frameEncoder",
//									new LengthFieldPrepender(2));
//							ch.pipeline().addLast("msgpack encoder",
//									new ObjectEncoder());
//							ch.pipeline().addLast(
//									new EchoClientHandler(sendNumber));
//						}
//					});
//
//			ChannelFuture future = b.connect(host, port).sync();
//			future.channel().closeFuture().sync();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} finally {
//			group.shutdownGracefully();
//		}
//	}
//
//	public static void main(String args[]) {
//		new EchoClient("127.0.0.1", 8000, 100).run();
//	}
//}
