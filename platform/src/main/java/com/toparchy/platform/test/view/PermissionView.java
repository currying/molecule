package com.toparchy.platform.test.view;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;
//import org.picketlink.idm.model.basic.User;


import com.toparchy.platform.security.RealmSelector;
import com.toparchy.platform.security.ejb.PermissionManager;
import com.toparchy.platform.security.model.User;

@Model
public class PermissionView {
	@EJB
	private PermissionManager permissionManager;

	private Role newRole;

	private User newUser;

	private Group newGroup;

	@Inject
	private RealmSelector realmSelector;

	@Produces
	@Named
	public Role getNewRole() {
		return newRole;
	}

	@Produces
	@Named
	public User getNewUser() {
		return newUser;
	}

	@Produces
	@Named
	public Group getNewGroup() {
		return newGroup;
	}

	@PostConstruct
	public void init() {
		newUser = new User();
		newRole = new Role();
		newGroup = new Group();
	}

	public void createRole() {
		permissionManager.createRole(realmSelector.select().getName(), newRole);
	}

	public void createUser() {
		permissionManager.createUser(realmSelector.select().getName(), newUser);
	}

	public void createGroup() {
		permissionManager.createGroup(realmSelector.select().getName(),
				newGroup);
	}
}
