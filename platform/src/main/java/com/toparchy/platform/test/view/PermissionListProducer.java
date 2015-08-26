package com.toparchy.platform.test.view;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Realm;
import org.picketlink.idm.model.basic.Role;

import com.toparchy.platform.security.RealmSelector;
import com.toparchy.platform.security.ejb.PermissionManager;
import com.toparchy.platform.security.model.User;

@Model
public class PermissionListProducer {
	@Inject
	private RealmSelector realmSelector;
	@EJB
	private PermissionManager permissionManager;

	@Produces
	@Named("realms")
	public List<Realm> getRealms() {
		return permissionManager.getRealms();
	}

	@Produces
	@Named("users")
	public List<User> getUsers() {
		return permissionManager.getUsersBy(realmSelector.select().getName(),
				"loginName", "admin");
	}

	@Produces
	@Named("groups")
	public List<Group> getGroups() {
		return permissionManager.getUserGroup(identity.getAccount());
	}

	@Inject
	private Identity identity;

	@Produces
	@Named("roles")
	public List<Role> getRoles() {
		List<Role> roles = new ArrayList<Role>();
		for (Group group : getGroups()) {
			roles.addAll(getGroupRoles(group));
		}
		return roles;
	}

	private List<Role> getGroupRoles(Group group) {
		List<Role> roles = new ArrayList<Role>();
		Group parentGroup = group.getParentGroup();
		roles.addAll(permissionManager.getGroupRoles(group));
		if (parentGroup != null)
			roles.addAll(getGroupRoles(parentGroup));
		return roles;
	}

	public String getStackTrace() {
		Throwable throwable = (Throwable) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap()
				.get("javax.servlet.error.exception");
		StringBuilder builder = new StringBuilder();
		builder.append(throwable.getMessage()).append("\n");
		for (StackTraceElement element : throwable.getStackTrace()) {
			builder.append(element).append("\n");
		}
		return builder.toString();
	}
}
