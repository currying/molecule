package com.toparchy.molecule.push.netty;

public class Client extends ClientPacket {

	private long expireTime;

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

}
