package com.toparchy.molecule.permission.model;

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
	private String nickName;

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

}
