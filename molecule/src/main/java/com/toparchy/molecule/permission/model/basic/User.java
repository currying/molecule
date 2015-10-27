package com.toparchy.molecule.permission.model.basic;

import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.IdentityStereotype;
import org.picketlink.idm.query.QueryParameter;

import static org.picketlink.idm.model.annotation.IdentityStereotype.Stereotype.USER;

@IdentityStereotype(USER)
public class User extends Agent {

	private static final long serialVersionUID = 4117586097100398485L;

	/**
	 * A query parameter used to set the firstName value.
	 */
	public static final QueryParameter FIRST_NAME = QUERY_ATTRIBUTE.byName("firstName");

	/**
	 * A query parameter used to set the lastName value.
	 */
	public static final QueryParameter LAST_NAME = QUERY_ATTRIBUTE.byName("lastName");

	/**
	 * A query parameter used to set the email value.
	 */
	public static final QueryParameter EMAIL = QUERY_ATTRIBUTE.byName("email");

	@AttributeProperty
	private String firstName;

	@AttributeProperty
	private String lastName;

	@AttributeProperty
	private String email;

	public User() {

	}

	public User(String loginName) {
		super(loginName);
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
