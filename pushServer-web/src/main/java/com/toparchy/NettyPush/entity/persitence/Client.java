package com.toparchy.NettyPush.entity.persitence;

import com.toparchy.NettyPush.entity.packet.ClientPacket;

public class Client extends ClientPacket {

	private long expireTime;

	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}


}
