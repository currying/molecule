package com.toparchy.platform.security.check;

import static org.picketlink.idm.model.basic.BasicModel.getGroup;
import static org.picketlink.idm.model.basic.BasicModel.getRole;
import static org.picketlink.idm.model.basic.BasicModel.hasRole;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;

@SessionScoped
public class AuthorizationChecker implements Serializable {

	private static final long serialVersionUID = -9156853617990418632L;

	@Inject
	private Identity identity;

	@Inject
	private IdentityManager identityManager;

	@Inject
	private RelationshipManager relationshipManager;

	public boolean hasApplicationRole(String roleName) {
		Role role = getRole(this.identityManager, roleName);
		return hasRole(this.relationshipManager, this.identity.getAccount(),
				role);
	}

	public boolean isMember(String groupName) {
		Group group = getGroup(this.identityManager, groupName);
		return BasicModel.isMember(this.relationshipManager,
				this.identity.getAccount(), group);
	}

	public boolean hasGroupRole(String roleName, String groupName) {
		Group group = getGroup(this.identityManager, groupName);
		Role role = getRole(this.identityManager, roleName);
		return BasicModel.hasGroupRole(this.relationshipManager,
				this.identity.getAccount(), role, group);
	}
}
