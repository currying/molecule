package com.toparchy.molecule.permission;

import java.util.Iterator;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;

@Stateless
@Named
public class LoginController {

	@Inject
	private Identity identity;

	@Inject
	private FacesContext facesContext;

	@Inject
	private OnlineAccountChecker onlineAccountChecker;

	public String login() {
		AuthenticationResult result = identity.login();
		if (AuthenticationResult.FAILED.equals(result)) {
			facesContext.addMessage(null,
					new FacesMessage("Authentication was unsuccessful.  Please check your username and password "
							+ "before trying again."));
			return null;
		}
		saveOnlineAccount(identity);
		return "success";
	}

	public String logout() {
		onlineAccountChecker.getOnlineAccount().remove(identity.getAccount().getId());
		identity.logout();
		return "success";
	}

	public void saveOnlineAccount(Identity identity) {
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Iterator<String> it = onlineAccountChecker.getOnlineAccount().keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.equals(identity.getAccount().getId())) {
				onlineAccountChecker.getOnlineAccount().get(key).invalidate();
			}
		}
		onlineAccountChecker.getOnlineAccount().put(identity.getAccount().getId(), session);
	}
}
