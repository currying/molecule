package com.toparchy.molecule.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 
 * 使用netty 自带进行 切包,粘包
 * 
 * @author flatychen
 * 
 */
public class SplitFrameDecoder extends LengthFieldBasedFrameDecoder {

	public SplitFrameDecoder(FrameHead frameHead) {
		super(frameHead.maxLength(), 0, frameHead.byteLength(), frameHead
				.headLength(), frameHead.byteLength());
	}

	/*
	 * 直接切除包头长度字节.防止内存拷贝
	 */
	@Override
	protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer,
			int index, int length) {
		// 增加一次引用计数
		buffer.retain();
		// 切聊包头长度字节
		return buffer.slice(index, length);
	}
}
