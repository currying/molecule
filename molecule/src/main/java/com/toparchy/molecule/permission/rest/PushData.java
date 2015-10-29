package com.toparchy.molecule.permission.rest;

public class PushData {
	private String deviceType;
	private String pushChannelId;
	private String pushUserId;
	private String userName;

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getPushChannelId() {
		return pushChannelId;
	}

	public void setPushChannelId(String pushChannelId) {
		this.pushChannelId = pushChannelId;
	}

	public String getPushUserId() {
		return pushUserId;
	}

	public void setPushUserId(String pushUserId) {
		this.pushUserId = pushUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
