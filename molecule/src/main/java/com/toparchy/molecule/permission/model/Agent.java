package com.toparchy.molecule.permission.model;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.IdentityStereotype;
import org.picketlink.idm.model.annotation.StereotypeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;

import static org.picketlink.idm.model.annotation.IdentityStereotype.Stereotype.USER;
import static org.picketlink.idm.model.annotation.StereotypeProperty.Property.IDENTITY_USER_NAME;

@IdentityStereotype(USER)
public class Agent extends AbstractIdentityType implements Account {

	private static final long serialVersionUID = 2915865002176741632L;

	public static final QueryParameter LOGIN_NAME = QUERY_ATTRIBUTE.byName("loginName");

	private String loginName;

	public Agent() {

	}

	public Agent(String loginName) {
		this.loginName = loginName;
	}

	@AttributeProperty
	@StereotypeProperty(IDENTITY_USER_NAME)
	@Unique
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
}
