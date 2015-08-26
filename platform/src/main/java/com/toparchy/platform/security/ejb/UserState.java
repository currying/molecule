package com.toparchy.platform.security.ejb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;

import com.toparchy.platform.security.model.User;

@Stateful
public class UserState implements Serializable {

	private static final long serialVersionUID = 7599009317576065595L;

	private User currentUser;

	@Inject
	private Identity identity;

	@Produces
	@Named("currentUser")
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@PostConstruct
	private void init() {
		currentUser = ((User) identity.getAccount());
	}

	@Remove
	public void clear() {
		currentUser = new User();
	}
}
