package com.toparchy.molecule.push;

import java.io.Serializable;
import java.util.Date;

public class PushMessageForm implements Serializable {

	private static final long serialVersionUID = 6398410963067491384L;
	private String channelId;
	private String userId;
	private String title;
	private String content;
	private int msgType;
	private int deviceType;
	private int expires = 5;
	private Date sendTime;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
