package com.toparchy.molecule.permission.model;

import org.picketlink.idm.IdentityManagementException;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.IdentityType;
import org.picketlink.idm.query.IdentityQuery;
import org.picketlink.idm.query.IdentityQueryBuilder;
import org.picketlink.idm.query.RelationshipQuery;

import java.util.List;

import static org.picketlink.common.util.StringUtil.isNullOrEmpty;
import static org.picketlink.idm.IDMMessages.MESSAGES;

public class BasicModel {

	public static Agent getAgent(IdentityManager identityManager, String loginName) throws IdentityManagementException {
		if (identityManager == null) {
			throw MESSAGES.nullArgument("IdentityManager");
		}

		if (isNullOrEmpty(loginName)) {
			return null;
		}

		IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
		List<Agent> agents = queryBuilder.createIdentityQuery(Agent.class)
				.where(queryBuilder.equal(Agent.LOGIN_NAME, loginName)).getResultList();

		if (agents.isEmpty()) {
			return null;
		} else if (agents.size() == 1) {
			return agents.get(0);
		} else {
			throw new IdentityManagementException("Error - multiple Agent objects found with same login name");
		}
	}

	public static User getUser(IdentityManager identityManager, String loginName) throws IdentityManagementException {
		if (identityManager == null) {
			throw MESSAGES.nullArgument("IdentityManager");
		}

		if (isNullOrEmpty(loginName)) {
			return null;
		}

		IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
		List<User> agents = queryBuilder.createIdentityQuery(User.class)
				.where(queryBuilder.equal(User.LOGIN_NAME, loginName)).getResultList();

		if (agents.isEmpty()) {
			return null;
		} else if (agents.size() == 1) {
			return agents.get(0);
		} else {
			throw new IdentityManagementException("Error - multiple Agent objects found with same login name");
		}
	}

	public static Role getRole(IdentityManager identityManager, String name) throws IdentityManagementException {
		if (identityManager == null) {
			throw MESSAGES.nullArgument("IdentityManager");
		}

		if (isNullOrEmpty(name)) {
			return null;
		}

		IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
		List<Role> roles = queryBuilder.createIdentityQuery(Role.class).where(queryBuilder.equal(Role.NAME, name))
				.getResultList();

		if (roles.isEmpty()) {
			return null;
		} else if (roles.size() == 1) {
			return roles.get(0);
		} else {
			throw new IdentityManagementException("Error - multiple Role objects found with same name");
		}
	}

	public static Group getGroup(IdentityManager identityManager, String groupPath) throws IdentityManagementException {
		if (identityManager == null) {
			throw MESSAGES.nullArgument("IdentityManager");
		}

		if (isNullOrEmpty(groupPath)) {
			return null;
		}

		if (!groupPath.startsWith("/")) {
			groupPath = "/" + groupPath;
		}

		Group group = null;
		String[] paths = groupPath.split("/");

		if (paths.length > 0) {
			String name = paths[paths.length - 1];
			IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
			IdentityQuery<Group> query = queryBuilder.createIdentityQuery(Group.class)
					.where(queryBuilder.equal(Group.NAME, name));

			List<Group> result = query.getResultList();

			for (Group storedGroup : result) {
				if (storedGroup.getPath().equals(groupPath)) {
					return storedGroup;
				}

				if (storedGroup.getPath().endsWith(groupPath)) {
					group = storedGroup;
				}
			}
		}

		return group;
	}

	public static Group getGroup(IdentityManager identityManager, String groupName, Group parent)
			throws IdentityManagementException {
		if (identityManager == null) {
			throw MESSAGES.nullArgument("IdentityManager");
		}

		if (groupName == null || parent == null) {
			return null;
		}

		return getGroup(identityManager, new Group(groupName, parent).getPath());
	}

	public static boolean isMember(RelationshipManager relationshipManager, Account member, Group group)
			throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (member == null) {
			throw MESSAGES.nullArgument("Account");
		}

		if (group == null) {
			throw MESSAGES.nullArgument("Group");
		}

		RelationshipQuery<GroupMembership> query = relationshipManager.createRelationshipQuery(GroupMembership.class);

		query.setParameter(GroupMembership.MEMBER, member);

		List<GroupMembership> result = query.getResultList();

		for (GroupMembership membership : result) {
			if (membership.getGroup().getId().equals(group.getId())) {
				return true;
			}

			if (membership.getGroup().getPath().startsWith(group.getPath())) {
				return true;
			}
		}

		return false;
	}

	public static void addToGroup(RelationshipManager relationshipManager, Account member, Group group)
			throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (member == null) {
			throw MESSAGES.nullArgument("Account");
		}

		if (group == null) {
			throw MESSAGES.nullArgument("Group");
		}

		relationshipManager.add(new GroupMembership(member, group));
	}

	public static void removeFromGroup(RelationshipManager relationshipManager, Account member, Group group)
			throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (member == null) {
			throw MESSAGES.nullArgument("Account");
		}

		if (group == null) {
			throw MESSAGES.nullArgument("Group");
		}

		RelationshipQuery<GroupMembership> query = relationshipManager.createRelationshipQuery(GroupMembership.class);

		query.setParameter(GroupMembership.MEMBER, member);
		query.setParameter(GroupMembership.GROUP, group);

		for (GroupMembership membership : query.getResultList()) {
			relationshipManager.remove(membership);
		}
	}

	public static boolean hasGroupRole(RelationshipManager relationshipManager, IdentityType assignee, Role role,
			Group group) throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (assignee == null) {
			throw MESSAGES.nullArgument("IdentityType");
		}

		if (role == null) {
			throw MESSAGES.nullArgument("Role");
		}

		if (group == null) {
			throw MESSAGES.nullArgument("Group");
		}

		RelationshipQuery<GroupRole> query = relationshipManager.createRelationshipQuery(GroupRole.class);

		query.setParameter(GroupRole.ASSIGNEE, assignee);
		query.setParameter(GroupRole.ROLE, role);

		List<GroupRole> result = query.getResultList();

		for (GroupRole membership : result) {
			if (membership.getGroup().getId().equals(group.getId())) {
				return true;
			}

			if (group.getPath().startsWith(membership.getGroup().getPath())) {
				return true;
			}
		}

		return false;
	}

	public static void grantGroupRole(RelationshipManager relationshipManager, IdentityType assignee, Role role,
			Group group) throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (assignee == null) {
			throw MESSAGES.nullArgument("IdentityType");
		}

		if (role == null) {
			throw MESSAGES.nullArgument("Role");
		}

		if (group == null) {
			throw MESSAGES.nullArgument("Group");
		}

		relationshipManager.add(new GroupRole(assignee, group, role));
	}

	public static void revokeGroupRole(RelationshipManager relationshipManager, IdentityType assignee, Role role,
			Group group) throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (assignee == null) {
			throw MESSAGES.nullArgument("IdentityType");
		}

		if (role == null) {
			throw MESSAGES.nullArgument("Role");
		}

		if (group == null) {
			throw MESSAGES.nullArgument("Group");
		}

		RelationshipQuery<GroupRole> query = relationshipManager.createRelationshipQuery(GroupRole.class);

		query.setParameter(GroupRole.ASSIGNEE, assignee);
		query.setParameter(GroupRole.GROUP, group);
		query.setParameter(GroupRole.ROLE, role);

		for (GroupRole groupRole : query.getResultList()) {
			relationshipManager.remove(groupRole);
		}
	}

	public static boolean hasRole(RelationshipManager relationshipManager, IdentityType assignee, Role role)
			throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (assignee == null) {
			throw MESSAGES.nullArgument("IdentityType");
		}

		if (!Account.class.isInstance(assignee) && !Group.class.isInstance(assignee)) {
			throw MESSAGES.unexpectedType(assignee.getClass());
		}

		if (role == null) {
			throw MESSAGES.nullArgument("Role");
		}

		RelationshipQuery<Grant> query = relationshipManager.createRelationshipQuery(Grant.class);

		query.setParameter(Grant.ASSIGNEE, assignee);
		query.setParameter(GroupRole.ROLE, role);

		boolean hasRole = !query.getResultList().isEmpty();

		if (!hasRole) {
			return relationshipManager.inheritsPrivileges(assignee, role);
		}

		return hasRole;
	}

	public static void grantRole(RelationshipManager relationshipManager, IdentityType assignee, Role role)
			throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (assignee == null) {
			throw MESSAGES.nullArgument("IdentityType");
		}

		if (!Account.class.isInstance(assignee) && !Group.class.isInstance(assignee)) {
			throw MESSAGES.unexpectedType(assignee.getClass());
		}

		if (role == null) {
			throw MESSAGES.nullArgument("Role");
		}

		relationshipManager.add(new Grant(assignee, role));
	}

	public static void revokeRole(RelationshipManager relationshipManager, IdentityType assignee, Role role)
			throws IdentityManagementException {
		if (relationshipManager == null) {
			throw MESSAGES.nullArgument("RelationshipManager");
		}

		if (assignee == null) {
			throw MESSAGES.nullArgument("IdentityType");
		}

		if (!Account.class.isInstance(assignee) && !Group.class.isInstance(assignee)) {
			throw MESSAGES.unexpectedType(assignee.getClass());
		}

		if (role == null) {
			throw MESSAGES.nullArgument("Role");
		}

		RelationshipQuery<Grant> query = relationshipManager.createRelationshipQuery(Grant.class);

		query.setParameter(Grant.ASSIGNEE, assignee);
		query.setParameter(Grant.ROLE, role);

		for (Grant grant : query.getResultList()) {
			relationshipManager.remove(grant);
		}
	}
}
