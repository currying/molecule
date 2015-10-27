package com.toparchy.molecule.push.netty;

import io.netty.channel.ChannelPipeline;

public class PushChannelInitializer extends AbstractChannelInitializer {

	private FrameHead frameHeader;

	@Override
	protected void initPipeline(ChannelPipeline pipeline) {
		frameHeader = new SimplePushHead();
		// 切包,粘包
		pipeline.addLast(new SplitFrameDecoder(frameHeader));
		// 解码
		pipeline.addLast(new PushFrameDecoder(frameHeader));

		// 编码器
		// 粘包
		pipeline.addLast(new MergeFrameEncoder(frameHeader));
		// 编码
		pipeline.addLast(new PushFrameEncoder(frameHeader));

		// 业务处理
		pipeline.addLast(new DeserializeHandler());

	}

	public void setFrameHeader(FrameHead frameHeader) {
		this.frameHeader = frameHeader;
	}

}
