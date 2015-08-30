package com.toparchy.molecule.permission;

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
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P00000001")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b;
	}

	@Secures
	@P00000002
	public boolean doP2Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P00000002")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b;
	}

	@Secures
	@P00000003
	public boolean doP3Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P00000003")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b;
	}

	@Secures
	@P00000004
	public boolean doP4Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P00000004")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b;
	}

	@Secures
	@P00000005
	public boolean doP5Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P00000005")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b;
	}

	@Secures
	@P00000006
	public boolean doP6Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P00000006")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b;
	}

	@Secures
	@P00000007
	public boolean doP7Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P00000007")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b;
	}

	@Secures
	@P00000008
	public boolean doP8Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationResource applicationResource : applicationResourceRepository.findByKey("P00000008")) {
			b = b || hasRole(relationshipManager, identity.getAccount(),
					getRole(identityManager, applicationResource.getApplicationRole().getKey()));
		}
		return b;
	}

}
