package com.toparchy.platform.security.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

import com.toparchy.platform.model.Person;
import com.toparchy.platform.security.model.User;

@Entity
@IdentityManaged(User.class)
public class AccountTypeEntity extends IdentityTypeEntity {

	private static final long serialVersionUID = -2329856433443491752L;

	@AttributeValue
	private String loginName;

	@AttributeValue
	private String activationCode;

	@AttributeValue
	@OneToOne(cascade = CascadeType.ALL)
	private Person person;

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getActivationCode() {
		return this.activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
