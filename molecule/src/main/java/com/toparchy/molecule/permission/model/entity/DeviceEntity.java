package com.toparchy.molecule.permission.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class DeviceEntity implements Serializable {

	private static final long serialVersionUID = 8330194909337507289L;
	@Id
	@Column(name = "DEVICES_ID", length = 50)
	@GeneratedValue(generator = "device-uuid")
	@GenericGenerator(name = "device-uuid", strategy = "uuid")
	private String deviceId;
	@Column(name = "DEVICE_TYPE", length = 50)
	private String deviceType;
	@Column(name = "DEVICE_CHANNELID", length = 100)
	private String channelId;
	private String userId;
	@Column(name = "DEVICE_STATE", length = 50)
	private String state;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private MemberEntity memberEntity;

	public DeviceEntity() {
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

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

	public MemberEntity getMemberEntity() {
		return memberEntity;
	}

	public void setMemberEntity(MemberEntity memberEntity) {
		this.memberEntity = memberEntity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
