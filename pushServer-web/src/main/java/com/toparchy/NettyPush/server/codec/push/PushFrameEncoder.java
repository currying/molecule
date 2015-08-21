package com.toparchy.NettyPush.server.codec.push;

import java.util.Arrays;

import com.toparchy.NettyPush.server.frame.FrameHead;
import com.toparchy.NettyPush.server.frame.SimplePushOutFrame;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PushFrameEncoder extends MessageToByteEncoder<String> {

	private FrameHead frameHead;

	public PushFrameEncoder(FrameHead frameHead) {
		super();
		this.frameHead = frameHead;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out)
			throws Exception {
		SimplePushOutFrame frame = new SimplePushOutFrame(frameHead, msg);
		out.writeBytes(frame.getHead());
		out.writeBytes(frame.getBody());
	}




}
