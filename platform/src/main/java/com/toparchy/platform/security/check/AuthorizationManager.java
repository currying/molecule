package com.toparchy.platform.security.check;

import static org.picketlink.idm.model.basic.BasicModel.getRole;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;

import org.apache.deltaspike.security.api.authorization.Secures;
import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.GroupMembership;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.query.RelationshipQuery;

import com.toparchy.platform.security.annotation.DeclareRoles;
import com.toparchy.platform.security.model.ApplicationRole;

@ApplicationScoped
public class AuthorizationManager {
	@Inject
	private Instance<Identity> identityInstance;

	@Inject
	private IdentityManager identityManager;

	@Inject
	private RelationshipManager relationshipManager;

	@Secures
	@DeclareRoles
	public boolean checkDeclaredRoles(InvocationContext invocationContext,
			BeanManager manager) throws Exception {
		Object targetBean = invocationContext.getTarget();
		DeclareRoles declareRoles = targetBean.getClass().getAnnotation(
				DeclareRoles.class);
		if (declareRoles == null) {
			declareRoles = invocationContext.getMethod().getAnnotation(
					DeclareRoles.class);
		}
		ApplicationRole[] requiredRoles = declareRoles.value();
		if (requiredRoles.length == 0) {
			throw new IllegalArgumentException(
					"@DeclaredRoles does not define any role.");
		}
		List<Group> groups = getUserGroup(getIdentity().getAccount());
		for (ApplicationRole requiredRole : requiredRoles) {
			if (groups.size() > 0) {
				for (Group group : groups) {
					if (hasRole(group, requiredRole.name())) {
						return true;
					}
				}
			} else if (hasRole(requiredRole.name()))
				return true;
		}
		return false;
	}

	private List<Group> getUserGroup(Account account) {
		RelationshipQuery<GroupMembership> query = relationshipManager
				.createRelationshipQuery(GroupMembership.class);
		query.setParameter(GroupMembership.MEMBER, account);
		List<Group> groups = new ArrayList<Group>();
		for (GroupMembership groupMembership : query.getResultList()) {
			groups.add(groupMembership.getGroup());
		}
		return groups;
	}

	private boolean hasRole(Group group, String roleName) {
		boolean check = true;
		Role role = getRole(identityManager, roleName);
		check = BasicModel.hasRole(relationshipManager, group, role);
		if (!check && group.getParentGroup() != null)
			check = hasRole(group.getParentGroup(), roleName);
		return check;
	}

	private boolean hasRole(String roleName) {
		boolean check = true;
		Account account = getIdentity().getAccount();
		Role role = getRole(identityManager, roleName);
		check = BasicModel.hasRole(relationshipManager, account, role);
		return check;
	}

	private Identity getIdentity() {
		return this.identityInstance.get();
	}
}
