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
import com.toparchy.molecule.permission.annotations.P00000009;
import com.toparchy.molecule.permission.annotations.P00000010;
import com.toparchy.molecule.permission.data.ApplicationResourceRepository;
import com.toparchy.molecule.permission.model.entity.ApplicationRole;

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
	public boolean doP00000001Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000001").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000002
	public boolean doP00000002Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000002").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000003
	public boolean doP00000003Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000003").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000004
	public boolean doP00000004Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000004").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000005
	public boolean doP00000005Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000005").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000006
	public boolean doP00000006Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000006").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000007
	public boolean doP00000007Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000007").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000008
	public boolean doP00000008Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000008").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000009
	public boolean doP00000009Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000009").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}

	@Secures
	@P00000010
	public boolean doP00000010Check(Identity identity, IdentityManager identityManager,
			RelationshipManager relationshipManager) throws Exception {
		boolean b = false;
		for (ApplicationRole role : applicationResourceRepository.findByKey("P00000010").getApplicationRoles()) {
			b = b || hasRole(relationshipManager, identity.getAccount(), getRole(identityManager, role.getKey()));
		}
		return b;
	}
}
