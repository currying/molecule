package com.toparchy.molecule.push.netty;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author flaty
 *
 */
public class MergeFrameEncoder extends LengthFieldPrepender {

	public MergeFrameEncoder(FrameHead frameHead) {
		super(frameHead.byteLength(), -frameHead.headLength(), false);
	}

}
