// package com.toparchy.molecule.permission.init;
//
// import static org.picketlink.idm.model.basic.BasicModel.addToGroup;
// import static org.picketlink.idm.model.basic.BasicModel.getGroup;
// import static org.picketlink.idm.model.basic.BasicModel.grantRole;
//
// import javax.annotation.PostConstruct;
// import javax.ejb.Singleton;
// import javax.ejb.Startup;
// import javax.inject.Inject;
//
// import org.picketlink.idm.IdentityManager;
// import org.picketlink.idm.PartitionManager;
// import org.picketlink.idm.RelationshipManager;
// import org.picketlink.idm.credential.Password;
// import org.picketlink.idm.model.basic.Group;
// import org.picketlink.idm.model.basic.Role;
//
// import com.toparchy.molecule.permission.model.Member;
//
// @Singleton
// @Startup
// public class SecurityInitializer {
//
// @Inject
// private PartitionManager partitionManager;
//
// @PostConstruct
// public void createUsers() {
// createGroup("adminstrators", "ADMINISTRATOR");
// createGroup("wzxt_group", "WZXT_MATERIALSTORAGE");
// createGroup("scxt_group", "SCXT_WORKINGHOURSVOLUME");
// addUser("admin", "adminstrators");
// addUser("user1", "wzxt_group");
// addUser("user2", "scxt_group");
// }
//
// private void createUser(String loginName, String roleName) {
// Member user = new Member(loginName);
// IdentityManager identityManager =
// this.partitionManager.createIdentityManager();
// identityManager.add(user);
// Password password = new Password(loginName);
// identityManager.updateCredential(user, password);
// Role role = new Role(roleName);
// identityManager.add(role);
// RelationshipManager relationshipManager =
// this.partitionManager.createRelationshipManager();
// grantRole(relationshipManager, user, role);
// }
//
// private void createGroup(String groupName, String roleName) {
// Group group = new Group(groupName);
// IdentityManager identityManager =
// this.partitionManager.createIdentityManager();
// identityManager.add(group);
// identityManager.update(group);
// Role role = new Role(roleName);
// identityManager.add(role);
// RelationshipManager relationshipManager =
// this.partitionManager.createRelationshipManager();
// grantRole(relationshipManager, group, role);
// }
//
// private void addUser(String loginName, String groupName) {
// Member user = new Member(loginName);
// IdentityManager identityManager =
// this.partitionManager.createIdentityManager();
// identityManager.add(user);
// Password password = new Password(loginName);
// identityManager.updateCredential(user, password);
// RelationshipManager relationshipManager =
// this.partitionManager.createRelationshipManager();
// addToGroup(relationshipManager, user, getGroup(identityManager, groupName));
// }
// }
