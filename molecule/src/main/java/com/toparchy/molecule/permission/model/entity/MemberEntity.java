package com.toparchy.molecule.permission.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

@IdentityManaged({ Member.class })
@Entity
public class MemberEntity extends IdentityTypeEntity {

	private static final long serialVersionUID = -3362716689864243268L;

	@AttributeValue
	private String loginName;

	@AttributeValue
	private String firstName;

	@AttributeValue
	private String lastName;

	@AttributeValue
	private String sex;

	@AttributeValue
	@Column(unique = true)
	private String nickName;

	@AttributeValue
	private String phoneNumber;

	@AttributeValue
	private String email;

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
