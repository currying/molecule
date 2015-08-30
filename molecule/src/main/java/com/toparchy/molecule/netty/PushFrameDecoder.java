package com.toparchy.molecule.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * 
 * 根据包头解码
 * 
 * @author flatychen
 * 
 */
public class PushFrameDecoder extends ByteToMessageDecoder {

	private FrameHead frameHead;

	public PushFrameDecoder(FrameHead frameHead) {
		super();
		this.frameHead = frameHead;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {

		int bytesHaveRead = in.readableBytes();

		// 读到0字节时且有读事件发生时为TCP rst重置
		if (bytesHaveRead == 0) {
			InetSocketAddress isa = (InetSocketAddress) ctx.channel()
					.remoteAddress();
			in.release();
			return;
		}

		byte[] bytes = new byte[bytesHaveRead];
		in.readBytes(bytes);
		// 包解码
		SimplePushInFrame frame = new SimplePushInFrame(frameHead, bytes);
		String _s = frame.getBody();
		out.add(_s);
	}

}
