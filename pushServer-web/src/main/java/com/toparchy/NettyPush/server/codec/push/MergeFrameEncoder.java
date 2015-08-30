package com.toparchy.NettyPush.server.codec.push;

import com.toparchy.NettyPush.server.frame.FrameHead;

import io.netty.handler.codec.LengthFieldPrepender;


/**
 * @author flaty
 *
 */
public class MergeFrameEncoder extends LengthFieldPrepender {

	public MergeFrameEncoder(FrameHead frameHead){
		super(frameHead.byteLength(),- frameHead.headLength(),false);
	}

}
