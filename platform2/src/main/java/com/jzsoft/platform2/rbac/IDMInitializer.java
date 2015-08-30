//package com.jzsoft.platform2.rbac;
//
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
//import org.picketlink.idm.model.basic.Role;
//import org.picketlink.idm.model.basic.User;
//
//@Singleton
//@Startup
//public class IDMInitializer {
//
//	@Inject
//	private PartitionManager partitionManager;
//
//	@PostConstruct
//	public void create() {
//		User currying = new User("currying");
//		User user1 = new User("user1");
//		User user2 = new User("user2");
//
//		IdentityManager identityManager = this.partitionManager
//				.createIdentityManager();
//		identityManager.add(currying);
//		identityManager.updateCredential(currying, new Password("822005"));
//		identityManager.add(user1);
//		identityManager.updateCredential(user1, new Password("user1"));
//		identityManager.add(user2);
//		identityManager.updateCredential(user2, new Password("user2"));
//
//		Role admin = new Role("admin");
//		identityManager.add(admin);
//		Role maintenanceMan = new Role("maintenance-man");
//		identityManager.add(maintenanceMan);
//		Role customer = new Role("customer");
//		identityManager.add(customer);
//		RelationshipManager relationshipManager = this.partitionManager
//				.createRelationshipManager();
//		grantRole(relationshipManager, currying, admin);
//		grantRole(relationshipManager, user1, maintenanceMan);
//		grantRole(relationshipManager, user2, customer);
//	}
//}
