package com.toparchy.molecule.permission;

import java.util.Iterator;
import java.util.Map;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;

@Stateless
@Named
public class LoginController {

	@Inject
	private Identity identity;

	@Inject
	private FacesContext facesContext;

	// @Inject
	// private OnlineAccountChecker onlineAccountChecker;

	public String login() {
		AuthenticationResult result = identity.login();
		if (AuthenticationResult.FAILED.equals(result)) {
			facesContext.addMessage(null,
					new FacesMessage("Authentication was unsuccessful.  Please check your username and password "
							+ "before trying again."));
			return null;
		}
		// saveOnlineAccount(identity);
		return "success";
	}

	public String logout() {
		// onlineAccountChecker.getOnlineAccount().remove(identity.getAccount().getId());
		identity.logout();
		return "success";
	}

	// public void saveOnlineAccount(Identity identity) {
	// Iterator<String> it =
	// onlineAccountChecker.getOnlineAccount().keySet().iterator();
	// while (it.hasNext()) {
	// String key = it.next();
	// // if (key.equals(onlineAccount.get(key).getAccount().getId()))
	// // onlineAccount.get(key).logout();
	// System.out.println(key + " 111 " +
	// onlineAccountChecker.getOnlineAccount().get(key).getAccount());
	// }
	// System.out.println(identity.getAccount().getId() + " 222 " +
	// identity.getAccount());
	// onlineAccountChecker.getOnlineAccount().put(identity.getAccount().getId(),
	// identity);
	// }
}
