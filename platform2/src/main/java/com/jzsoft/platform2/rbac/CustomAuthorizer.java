//package com.jzsoft.platform2.rbac;
//
//import org.apache.deltaspike.security.api.authorization.Secures;
//import org.picketlink.Identity;
//import org.picketlink.idm.IdentityManager;
//import org.picketlink.idm.RelationshipManager;
//
//import javax.enterprise.context.ApplicationScoped;
//
//import static org.picketlink.idm.model.basic.BasicModel.*;
//
//@ApplicationScoped
//public class CustomAuthorizer {
//
//	@Secures
//	@Admin
//	public boolean doAdminCheck(Identity identity,
//			IdentityManager identityManager,
//			RelationshipManager relationshipManager) throws Exception {
//		return hasRole(relationshipManager, identity.getAccount(),
//				getRole(identityManager, "admin"));
//	}
//
//	@Secures
//	@MaintenanceMan
//	public boolean doMaintenanceManCheck(Identity identity,
//			IdentityManager identityManager,
//			RelationshipManager relationshipManager) throws Exception {
//		return hasRole(relationshipManager, identity.getAccount(),
//				getRole(identityManager, "maintenance-man"))
//				|| hasRole(relationshipManager, identity.getAccount(),
//						getRole(identityManager, "admin"));
//	}
//
//	@Secures
//	@Customer
//	public boolean doCustomerCheck(Identity identity,
//			IdentityManager identityManager,
//			RelationshipManager relationshipManager) throws Exception {
//		return hasRole(relationshipManager, identity.getAccount(),
//				getRole(identityManager, "customer"))
//				|| hasRole(relationshipManager, identity.getAccount(),
//						getRole(identityManager, "admin"));
//	}
//
//}
