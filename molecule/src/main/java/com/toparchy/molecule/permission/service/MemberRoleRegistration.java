package com.toparchy.molecule.permission.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;

import com.toparchy.molecule.permission.model.ApplicationRole;
import com.toparchy.molecule.permission.model.Member;

@Stateless
public class MemberRoleRegistration {
	@Inject
	private EntityManager moleculeEm;
	@Inject
	private PartitionManager partitionManager;
	@Inject
	private RelationshipManager relationshipManager;
	@Inject
	private IdentityManager identityManager;

	public void grantRoleToMember(Member member, ApplicationRole applicationRole) {
		Role role = BasicModel.getRole(identityManager, applicationRole.getKey());
		if (!BasicModel.hasRole(relationshipManager, member, role))
			BasicModel.grantRole(relationshipManager, member, role);
	}

	public void revokeRoleFromMember(Member member, ApplicationRole applicationRole) {
		Role role = BasicModel.getRole(identityManager, applicationRole.getKey());
		BasicModel.revokeRole(relationshipManager, member, role);
	}
}
