package com.toparchy.molecule.permission;

import static com.toparchy.molecule.permission.ApplicationRole.*;
import static org.picketlink.idm.model.basic.BasicModel.getRole;
import static org.picketlink.idm.model.basic.BasicModel.hasRole;

import javax.enterprise.context.ApplicationScoped;

import org.apache.deltaspike.security.api.authorization.Secures;
import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;

import com.toparchy.molecule.permission.annotations.*;

@ApplicationScoped
public class CustomAuthorizer {

	@Secures
	@Administrator
	public boolean doAdminCheck(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	@Secures
	@P1
	public boolean doP1Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, WZXT_MATERIALSTORAGE))
				|| hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	@Secures
	@P2
	public boolean doP2Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, WZXT_MATERIALSTORAGE))
				|| hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	@Secures
	@P3
	public boolean doP3Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, WZXT_MATERIALSTORAGE))
				|| hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	@Secures
	@P4
	public boolean doP4Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, WZXT_MATERIALSTORAGE))
				|| hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	@Secures
	@P5
	public boolean doP5Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, WZXT_MATERIALSTORAGE))
				|| hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	@Secures
	@P6
	public boolean doP6Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, WZXT_MATERIALSTORAGE))
				|| hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	@Secures
	@P7
	public boolean doP7Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, SCXT_WORKINGHOURSVOLUME))
				|| hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	@Secures
	@P8
	public boolean doP8Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, SCXT_WORKINGHOURSVOLUME))
				|| hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, ADMINISTRATOR));
	}

	//
	// @Secures
	// @NewData
	// public boolean doNewDataCheck(Identity identity, IdentityManager
	// identityManager,
	// RelationshipManager relationshipManager) throws Exception {
	// return hasRole(relationshipManager, identity.getAccount(),
	// getRole(identityManager, NEW_DATA))
	// || hasRole(relationshipManager, identity.getAccount(),
	// getRole(identityManager, ADMINISTRATOR));
	// }
	//
	// @Secures
	// @DeleteData
	// public boolean doDeleteDataCheck(Identity identity, IdentityManager
	// identityManager,
	// RelationshipManager relationshipManager) throws Exception {
	// return hasRole(relationshipManager, identity.getAccount(),
	// getRole(identityManager, DELETE_DATA))
	// || hasRole(relationshipManager, identity.getAccount(),
	// getRole(identityManager, ADMINISTRATOR));
	// }
	//
	// @Secures
	// @UpdateData
	// public boolean doUpdateDataCheck(Identity identity, IdentityManager
	// identityManager,
	// RelationshipManager relationshipManager) throws Exception {
	// return hasRole(relationshipManager, identity.getAccount(),
	// getRole(identityManager, UPDATE_DATA))
	// || hasRole(relationshipManager, identity.getAccount(),
	// getRole(identityManager, ADMINISTRATOR));
	// }
	//
	// @Secures
	// @SelectData
	// public boolean doSelectDataCheck(Identity identity, IdentityManager
	// identityManager,
	// RelationshipManager relationshipManager) throws Exception {
	// return hasRole(relationshipManager, identity.getAccount(),
	// getRole(identityManager, SELECT_DATA))
	// || hasRole(relationshipManager, identity.getAccount(),
	// getRole(identityManager, ADMINISTRATOR));
	// }
}
