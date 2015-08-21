package com.toparchy.molecule.netty;

/**
 * 
 * 简单推送包头定义<br>
 * 4字节长度, 4字节包头
 * 
 * @author flatychen
 * 
 */
public class SimplePushHead implements FrameHead {

	public final int FRAME_LENGTH_BYTES = 4;

	public final int MAX_LENGTH = Integer.MAX_VALUE;

	public final int HEAD_LENGTH_BYTES = 4;

	@Override
	public int byteLength() {
		return FRAME_LENGTH_BYTES;
	}

	@Override
	public int maxLength() {
		return MAX_LENGTH;
	}

	@Override
	public int headLength() {
		return HEAD_LENGTH_BYTES;
	}

	@Override
	public int bytesToInt(byte[] b) {
		if (b.length != this.FRAME_LENGTH_BYTES) {
			throw new IllegalArgumentException("----> 包长度数组非法");
		}
		return ByteUtil.byteArrayToInt(b);
	}

	@Override
	public byte[] intToBytes(int length) {
		return ByteUtil.intToByteArray(length);
	}

}
