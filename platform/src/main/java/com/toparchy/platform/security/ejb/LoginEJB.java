package com.toparchy.platform.security.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;

@Stateless
public class LoginEJB {

	@Inject
	// @Identity.Stateless
	private Identity identity;

	// @Inject
	// private DefaultLoginCredentials loginCredentials;

	public boolean login() {
		AuthenticationResult result = identity.login();
		if (AuthenticationResult.FAILED.equals(result)) {
			return false;
		}
		return true;
	}

	public void logout() {
		this.identity.logout();
	}
}
