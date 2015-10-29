package com.toparchy.molecule.permission.model;

import static org.picketlink.idm.model.annotation.IdentityStereotype.Stereotype.USER;
import static org.picketlink.idm.model.annotation.StereotypeProperty.Property.IDENTITY_USER_NAME;

import java.util.HashSet;
import java.util.Set;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.IdentityStereotype;
import org.picketlink.idm.model.annotation.StereotypeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;

import com.toparchy.molecule.permission.model.entity.DeviceEntity;

@IdentityStereotype(USER)
public class Member extends AbstractIdentityType implements Account {

	private static final long serialVersionUID = -4368503151778824869L;
	public static final QueryParameter FIRST_NAME = QUERY_ATTRIBUTE.byName("firstName");
	public static final QueryParameter LAST_NAME = QUERY_ATTRIBUTE.byName("lastName");
	public static final QueryParameter SEX = QUERY_ATTRIBUTE.byName("sex");
	public static final QueryParameter NICK_NAME = QUERY_ATTRIBUTE.byName("nickName");
	public static final QueryParameter EMAIL = QUERY_ATTRIBUTE.byName("email");
	public static final QueryParameter LOGIN_NAME = QUERY_ATTRIBUTE.byName("loginName");
	@AttributeProperty
	private String firstName;
	@AttributeProperty
	private String lastName;
	@AttributeProperty
	private String sex;
	@AttributeProperty
	private String nickName;
	@AttributeProperty
	private String email;
	@AttributeProperty
	private String phoneNumber;
	@AttributeProperty
	private String loginName;
	@AttributeProperty
	private Set<DeviceEntity> devices = new HashSet<DeviceEntity>();

	private String passWord;

	public Member() {
	}

	public Member(String loginName, String firstName, String lastName, String nickName, String email,
			String phoneNumber) {
		this.loginName = loginName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@StereotypeProperty(IDENTITY_USER_NAME)
	@Unique
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Set<DeviceEntity> getDevices() {
		return devices;
	}

	public void addDevice(DeviceEntity device) {
		devices.add(device);
	}

	public void removeDeivce(DeviceEntity device) {
		devices.remove(device);
	}

	public void setDevices(Set<DeviceEntity> devices) {
		this.devices = devices;
	}
}
