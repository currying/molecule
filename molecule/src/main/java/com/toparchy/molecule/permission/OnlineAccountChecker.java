package com.toparchy.molecule.permission;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class OnlineAccountChecker {
	private Map<String, HttpSession> onlineAccount = new HashMap<String, HttpSession>();

	public Map<String, HttpSession> getOnlineAccount() {
		return onlineAccount;
	}

	public void setOnlineAccount(Map<String, HttpSession> onlineAccount) {
		this.onlineAccount = onlineAccount;
	}
}
