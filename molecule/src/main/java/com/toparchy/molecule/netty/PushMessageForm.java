package com.toparchy.molecule.netty;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 
 * 推送消处表单
 * 
 * @author flatychen
 * 
 */
public class PushMessageForm implements Serializable {

	private static final long serialVersionUID = -199209507778458986L;

	private Client client;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	private int pushAction;

	private String openUrl;

	private String openActivity;

	private Date expireDate;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getPushAction() {
		return pushAction;
	}

	public void setPushAction(int pushAction) {
		this.pushAction = pushAction;
	}

	public void setPushAction(byte pushAction) {
		this.pushAction = pushAction;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public String getOpenActivity() {
		return openActivity;
	}

	public void setOpenActivity(String openActivity) {
		this.openActivity = openActivity;
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

	public PushMessagePacket parsePushMessage() {
		PushMessagePacket pm = new PushMessagePacket();

		try {
			PropertyUtils.copyProperties(pm, this);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return pm;
	}

}
