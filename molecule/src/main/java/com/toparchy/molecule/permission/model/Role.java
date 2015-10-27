package com.toparchy.molecule.permission.model;

import static org.picketlink.idm.model.annotation.IdentityStereotype.Stereotype.ROLE;
import static org.picketlink.idm.model.annotation.StereotypeProperty.Property.IDENTITY_ROLE_NAME;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.IdentityStereotype;
import org.picketlink.idm.model.annotation.StereotypeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;

@IdentityStereotype(ROLE)
public class Role extends AbstractIdentityType {

	private static final long serialVersionUID = -9044601754527766512L;

	public static final QueryParameter NAME = QUERY_ATTRIBUTE.byName("name");
	public static final QueryParameter ALIASNAME = QUERY_ATTRIBUTE.byName("aliasName");

	private String name;

	private String aliasName;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@AttributeProperty
	@StereotypeProperty(IDENTITY_ROLE_NAME)
	@Unique
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@AttributeProperty
	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
}
