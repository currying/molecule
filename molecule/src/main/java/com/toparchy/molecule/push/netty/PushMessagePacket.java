package com.toparchy.molecule.push.netty;

/**
 * 推送文本消息报文
 * 
 * @author flatychen
 * 
 */
public class PushMessagePacket {

	@Override
	public String toString() {
		return "PushMessagePacket [title=" + title + ", content=" + content
				+ ", pushActionMixin=" + pushActionMixin + "]";
	}

	private String title;

	private String content;

	private String pushActionMixin;

	public String getPushActionMixin() {
		return pushActionMixin;
	}

	public void setPushActionMixin(String pushActionMixin) {
		this.pushActionMixin = pushActionMixin;
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

}
