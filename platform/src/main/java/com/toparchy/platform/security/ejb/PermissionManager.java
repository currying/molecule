package com.toparchy.platform.security.ejb;

import static com.toparchy.platform.security.model.ApplicationRole.GROUPMANAGER;
import static com.toparchy.platform.security.model.ApplicationRole.GROUPNORMALUSER;
import static com.toparchy.platform.security.model.ApplicationRole.REALMMANAGER;
import static com.toparchy.platform.security.model.ApplicationRole.REALMNORMALUSER;
import static com.toparchy.platform.security.model.ApplicationRole.ROLEMANAGER;
import static com.toparchy.platform.security.model.ApplicationRole.ROLEMNORMALUSER;
import static com.toparchy.platform.security.model.ApplicationRole.USERMANAGER;
import static com.toparchy.platform.security.model.ApplicationRole.USERNORMALUSER;
import static org.picketlink.idm.model.basic.BasicModel.addToGroup;
import static org.picketlink.idm.model.basic.BasicModel.grantGroupRole;
import static org.picketlink.idm.model.basic.BasicModel.grantRole;
import static org.picketlink.idm.model.basic.BasicModel.removeFromGroup;
import static org.picketlink.idm.model.basic.BasicModel.revokeGroupRole;
import static org.picketlink.idm.model.basic.BasicModel.revokeRole;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.IdentityType;
import org.picketlink.idm.model.basic.Grant;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.GroupMembership;
import org.picketlink.idm.model.basic.Realm;
import org.picketlink.idm.model.basic.Role;
//import org.picketlink.idm.model.basic.User;
import org.picketlink.idm.query.IdentityQuery;
import org.picketlink.idm.query.RelationshipQuery;

import com.toparchy.platform.security.annotation.DeclareRoles;
import com.toparchy.platform.security.model.User;

@Stateless
public class PermissionManager {
	@Inject
	private PartitionManager partitionManager;

	private IdentityManager identityManager;

	private RelationshipManager relationshipManager;

	@DeclareRoles({ ROLEMANAGER })
	public void createRole(String realmName, Role newRole) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		identityManager.add(newRole);
	}

	@DeclareRoles({ ROLEMANAGER })
	public void deleteRole(String realmName, Role role) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		identityManager.remove(role);
	}

	@DeclareRoles({ USERMANAGER })
	public void createUser(String realmName, User newUser) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		identityManager.add(newUser);
	}

	@DeclareRoles({ USERMANAGER })
	public void deleteUser(String realmName, User user) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		identityManager.remove(user);
	}

	@DeclareRoles({ GROUPMANAGER })
	public void createGroup(String realmName, Group newGroup) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		identityManager.add(newGroup);
	}

	@DeclareRoles({ GROUPMANAGER })
	public void deleteGroup(String realmName, Group group) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		identityManager.remove(group);
	}

	@DeclareRoles({ USERMANAGER, GROUPMANAGER })
	public void addUserToGroup(String realmName, User user, Group group) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		addToGroup(relationshipManager, user, group);
	}

	@DeclareRoles({ USERMANAGER, GROUPMANAGER })
	public void removeUserFromGroup(String realmName, User user, Group group) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		removeFromGroup(relationshipManager, user, group);
	}

	@DeclareRoles({ USERMANAGER, ROLEMANAGER })
	public void grantRoletoUser(String realmName, User user, Role role) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		grantRole(relationshipManager, user, role);
	}

	@DeclareRoles({ USERMANAGER, ROLEMANAGER })
	public void revokeRoleFromUser(String realmName, User user, Role role) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		revokeRole(relationshipManager, user, role);
	}

	@DeclareRoles({ USERMANAGER, ROLEMANAGER })
	public void grantRoletoUser(String realmName, User user, List<Role> roles) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		for (Role role : roles)
			grantRole(relationshipManager, user, role);
	}

	@DeclareRoles({ USERMANAGER, ROLEMANAGER })
	public void revokeRoleFromUser(String realmName, User user, List<Role> roles) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		for (Role role : roles)
			revokeRole(relationshipManager, user, role);
	}

	@DeclareRoles({ GROUPMANAGER, ROLEMANAGER })
	public void grantRoletoGroup(String realmName, Group group, Role role) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		grantRole(relationshipManager, group, role);
	}

	@DeclareRoles({ GROUPMANAGER, ROLEMANAGER })
	public void revokeRoleFromGroup(String realmName, Group group, Role role) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		revokeRole(relationshipManager, group, role);
	}

	@DeclareRoles({ GROUPMANAGER, ROLEMANAGER })
	public void grantRoletoGroup(String realmName, Group group, List<Role> roles) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		for (Role role : roles)
			grantRole(relationshipManager, group, role);
	}

	@DeclareRoles({ GROUPMANAGER, ROLEMANAGER })
	public void revokeRoleFromGroup(String realmName, Group group,
			List<Role> roles) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		for (Role role : roles)
			revokeRole(relationshipManager, group, role);
	}

	@DeclareRoles({ USERMANAGER, GROUPMANAGER, ROLEMANAGER })
	public void grantGroupRoleToUser(String realmName, User user, Role role,
			Group group) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		grantGroupRole(relationshipManager, user, role, group);
	}

	@DeclareRoles({ USERMANAGER, GROUPMANAGER, ROLEMANAGER })
	public void revokeGroupRoleFromUser(String realmName, User user, Role role,
			Group group) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		relationshipManager = this.partitionManager.createRelationshipManager();
		revokeGroupRole(relationshipManager, user, role, group);
	}

	@DeclareRoles({ REALMMANAGER, REALMNORMALUSER })
	public List<Realm> getRealms() {
		return partitionManager.getPartitions(Realm.class);
	}

	@DeclareRoles({ USERMANAGER, USERNORMALUSER })
	public List<User> getUsers(String realmName) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		IdentityQuery<User> query = identityManager
				.<User> createIdentityQuery(User.class);
		return query.getResultList();
	}

	@DeclareRoles({ USERMANAGER, USERNORMALUSER })
	public List<User> getUsersBy(String realmName, String param, String value) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		IdentityQuery<User> query = identityManager
				.<User> createIdentityQuery(User.class);
		query.setParameter(IdentityType.QUERY_ATTRIBUTE.byName(param), value);
		return query.getResultList();
	}

	@DeclareRoles({ USERMANAGER, ROLEMANAGER })
	public List<Role> getUserRoles(Account account) {
		RelationshipQuery<Grant> query = partitionManager
				.createRelationshipManager().createRelationshipQuery(
						Grant.class);
		query.setParameter(Grant.ASSIGNEE, account);
		List<Role> roles = new ArrayList<Role>();
		for (Grant grant : query.getResultList()) {
			roles.add(grant.getRole());
		}
		return roles;
	}

	@DeclareRoles({ USERMANAGER, USERNORMALUSER, GROUPMANAGER })
	public List<Group> getUserGroup(Account account) {
		RelationshipQuery<GroupMembership> query = partitionManager
				.createRelationshipManager().createRelationshipQuery(
						GroupMembership.class);
		query.setParameter(GroupMembership.MEMBER, account);
		List<Group> groups = new ArrayList<Group>();
		for (GroupMembership groupMembership : query.getResultList()) {
			groups.add(groupMembership.getGroup());
		}
		return groups;
	}

	@DeclareRoles({ GROUPMANAGER, GROUPNORMALUSER })
	public List<Group> getGroups(String realmName) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		IdentityQuery<Group> query = identityManager
				.<Group> createIdentityQuery(Group.class);
		return query.getResultList();
	}

	@DeclareRoles({ GROUPMANAGER, GROUPNORMALUSER })
	public List<Group> getGroupsBy(String realmName, String param, String value) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		IdentityQuery<Group> query = identityManager
				.<Group> createIdentityQuery(Group.class);
		query.setParameter(IdentityType.QUERY_ATTRIBUTE.byName(param), value);
		return query.getResultList();
	}

	@DeclareRoles({ GROUPMANAGER, GROUPNORMALUSER, ROLEMANAGER })
	public List<Role> getGroupRoles(Group group) {
		RelationshipQuery<Grant> query = partitionManager
				.createRelationshipManager().createRelationshipQuery(
						Grant.class);
		query.setParameter(Grant.ASSIGNEE, group);
		List<Role> roles = new ArrayList<Role>();
		for (Grant grant : query.getResultList()) {
			roles.add(grant.getRole());
		}
		return roles;
	}

	@DeclareRoles({ ROLEMANAGER, ROLEMNORMALUSER })
	public List<Role> getRoles(String realmName) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		IdentityQuery<Role> query = identityManager
				.<Role> createIdentityQuery(Role.class);
		return query.getResultList();
	}

	@DeclareRoles({ ROLEMANAGER, ROLEMNORMALUSER })
	public List<Role> getRolesBy(String realmName, String param, String value) {
		identityManager = this.partitionManager
				.createIdentityManager(partitionManager.getPartition(
						Realm.class, realmName));
		IdentityQuery<Role> query = identityManager
				.<Role> createIdentityQuery(Role.class);
		query.setParameter(IdentityType.QUERY_ATTRIBUTE.byName(param), value);
		return query.getResultList();
	}
}
