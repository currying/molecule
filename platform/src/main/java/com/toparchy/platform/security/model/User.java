package com.toparchy.platform.security.model;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.IdentityStereotype;
import org.picketlink.idm.model.annotation.StereotypeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.AttributeParameter;
import org.picketlink.idm.query.QueryParameter;

import com.toparchy.platform.model.Person;

import static org.picketlink.idm.model.annotation.IdentityStereotype.Stereotype.USER;
import static org.picketlink.idm.model.annotation.StereotypeProperty.Property.IDENTITY_USER_NAME;

@IdentityStereotype(USER)
public class User extends AbstractIdentityType implements Account {

	private static final long serialVersionUID = 2052641864390865536L;

	public static final AttributeParameter ACTIVATION_CODE = QUERY_ATTRIBUTE
			.byName("activationCode");

	public static final QueryParameter LOGIN_NAME = QUERY_ATTRIBUTE
			.byName("loginName");

	@AttributeProperty
	@Unique
	@StereotypeProperty(IDENTITY_USER_NAME)
	private String loginName;

	@AttributeProperty
	private String activationCode;

	@AttributeProperty
	private Person person;

	public User() {
		this(null);
	}

	public User(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public void invalidateActivationCode() {
		this.activationCode = null;
	}

	public String getActivationCode() {
		return this.activationCode;
	}
}
