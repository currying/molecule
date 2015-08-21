package com.toparchy.platform.security.view;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.toparchy.platform.security.ejb.LoginEJB;
import com.toparchy.platform.security.ejb.UserState;

@Model
public class LoginView {
	@EJB
	private LoginEJB loginEJB;
	@EJB
	private UserState userState;
	@Inject
	private FacesContext facesContext;

	public void login() {
		boolean login = loginEJB.login();
		if (!login)
			facesContext.addMessage(null, new FacesMessage(
					"Authentication was unsuccessful.  Please check your username and password "
							+ "before trying again."));
	}

	public void logout() {
		loginEJB.logout();
		userState.clear();
	}
}
