//package com.toparchy.serial;
//
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EchoClientHandler extends ChannelHandlerAdapter {
//	private final int sendNumber;
//
//	public EchoClientHandler(int sendNumber) {
//		this.sendNumber = sendNumber;
//	}
//
//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		List<UserInfo> userInfos = UserInfo();
//		for (UserInfo info : userInfos) {
//			ctx.write(info);
//		}
//		ctx.flush();
//	}
//
//	private List<UserInfo> UserInfo() {
//		List<UserInfo> userInfos = new ArrayList<UserInfo>();
//		UserInfo userInfo = null;
//		for (int i = 0; i < sendNumber; i++) {
//			userInfo = new UserInfo();
//			userInfo.setName("ABCDEFG --->" + i);
//			userInfo.setAge(i);
//			userInfos.add(userInfo);
//		}
//		return userInfos;
//	}
//
//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg)
//			throws Exception {
//		System.out.println("Client receive the msgpack message : " + msg);
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
//			throws Exception {
//		cause.printStackTrace();
//		ctx.close();
//	}
//
//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		ctx.flush();
//	}
//}