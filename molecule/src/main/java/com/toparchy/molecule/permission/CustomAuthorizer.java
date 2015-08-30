package com.toparchy.molecule.permission;

//import static com.toparchy.molecule.permission.ApplicationRole.*;
import static org.picketlink.idm.model.basic.BasicModel.getRole;
import static org.picketlink.idm.model.basic.BasicModel.hasRole;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.deltaspike.security.api.authorization.Secures;
import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;

import com.toparchy.molecule.permission.annotations.Administrator;
import com.toparchy.molecule.permission.annotations.P00000001;
import com.toparchy.molecule.permission.annotations.P00000002;
import com.toparchy.molecule.permission.annotations.P00000003;
import com.toparchy.molecule.permission.annotations.P00000004;
import com.toparchy.molecule.permission.annotations.P00000005;
import com.toparchy.molecule.permission.annotations.P00000006;
import com.toparchy.molecule.permission.annotations.P00000007;
import com.toparchy.molecule.permission.annotations.P00000008;
import com.toparchy.molecule.permission.data.ApplicationResourceRepository;
import com.toparchy.molecule.permission.model.ApplicationResource;

@ApplicationScoped
public class CustomAuthorizer {
	@Inject
	private ApplicationResourceRepository applicationResourceRepository;

	@Secures
	@Administrator
	public boolean doAdminCheck(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		return hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

	@Secures
	@P00000001
	public boolean doP1Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P1")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

	@Secures
	@P00000002
	public boolean doP2Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P2")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

	@Secures
	@P00000003
	public boolean doP3Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P3")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

	@Secures
	@P00000004
	public boolean doP4Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P4")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

	@Secures
	@P00000005
	public boolean doP5Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P5")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

	@Secures
	@P00000006
	public boolean doP6Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P6")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

	@Secures
	@P00000007
	public boolean doP7Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P7")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

	@Secures
	@P00000008
	public boolean doP8Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P8")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, "ADMINISTRATOR"));
	}

}
