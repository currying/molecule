//package com.toparchy.molecule.permission.init;
//
//import static com.toparchy.molecule.permission.ApplicationRole.ADMINISTRATOR;
//import static com.toparchy.molecule.permission.ApplicationRole.SCXT_WORKINGHOURSVOLUME;
//import static com.toparchy.molecule.permission.ApplicationRole.WZXT_MATERIALSTORAGE;
//import static org.picketlink.idm.model.basic.BasicModel.addToGroup;
//import static org.picketlink.idm.model.basic.BasicModel.getGroup;
//import static org.picketlink.idm.model.basic.BasicModel.grantRole;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//import javax.inject.Inject;
//
//import org.picketlink.idm.IdentityManager;
//import org.picketlink.idm.PartitionManager;
//import org.picketlink.idm.RelationshipManager;
//import org.picketlink.idm.credential.Password;
//import org.picketlink.idm.model.basic.Group;
//import org.picketlink.idm.model.basic.Role;
//
//import com.toparchy.molecule.permission.model.Member;
//
//@Singleton
//@Startup
//public class SecurityInitializer {
//
//	@Inject
//	private PartitionManager partitionManager;
//
//	@PostConstruct
//	public void createUsers() {
//		// createUser("admin", ADMINISTRATOR);
//		// createUser("user1", WZXT_MATERIALSTORAGE);
//		// createUser("user2", SCXT_WORKINGHOURSVOLUME);
//		createGroup("adminstrator", ADMINISTRATOR);
//		createGroup("wzxt", WZXT_MATERIALSTORAGE);
//		createGroup("scxt", SCXT_WORKINGHOURSVOLUME);
//		addUser("admin", "adminstrator");
//		addUser("user1", "wzxt");
//		addUser("user2", "scxt");
//	}
//
//	private void createUser(String loginName, String roleName) {
//		Member user = new Member(loginName);
//		IdentityManager identityManager = this.partitionManager.createIdentityManager();
//		identityManager.add(user);
//		Password password = new Password(loginName);
//		identityManager.updateCredential(user, password);
//		Role role = new Role(roleName);
//		identityManager.add(role);
//		RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();
//		grantRole(relationshipManager, user, role);
//	}
//
//	private void createGroup(String groupName, String roleName) {
//		Group group = new Group(groupName);
//		IdentityManager identityManager = this.partitionManager.createIdentityManager();
//		identityManager.add(group);
//		identityManager.update(group);
//		Role role = new Role(roleName);
//		identityManager.add(role);
//		RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();
//		grantRole(relationshipManager, group, role);
//	}
//
//	private void addUser(String loginName, String groupName) {
//		Member user = new Member(loginName);
//		IdentityManager identityManager = this.partitionManager.createIdentityManager();
//		identityManager.add(user);
//		Password password = new Password(loginName);
//		identityManager.updateCredential(user, password);
//		RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();
//		addToGroup(relationshipManager, user, getGroup(identityManager, groupName));
//	}
//}
