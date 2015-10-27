package com.toparchy.molecule.permission.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;

import com.toparchy.molecule.permission.model.SystemResource;
import com.toparchy.molecule.permission.model.ApplicationRole;

@Stateless
public class RoleResourceRegistration {
	// @PersistenceContext(unitName = "molecule")
	@Inject
	private EntityManager moleculeEm;
	@Inject
	private PartitionManager partitionManager;

	@Inject
	private Event<ApplicationRole> applicationRoleEventSrc;

	public void add(ApplicationRole applicationRole, SystemResource systemResource) {
		ApplicationRole role = moleculeEm.find(ApplicationRole.class, applicationRole.getId());
		SystemResource resource = moleculeEm.find(SystemResource.class, systemResource.getId());
		role.addApplicationResource(resource);
		// applicationRole.addApplicationResource(applicationResource);
		moleculeEm.merge(role);
		moleculeEm.flush();
		applicationRoleEventSrc.fire(role);
	}

	public void remove(ApplicationRole applicationRole, SystemResource systemResource) {
		ApplicationRole role = moleculeEm.find(ApplicationRole.class, applicationRole.getId());
		SystemResource resource = moleculeEm.find(SystemResource.class, systemResource.getId());
		role.removeApplicationResource(resource);
		// applicationRole.removeApplicationResource(applicationResource);
		moleculeEm.merge(role);
		moleculeEm.flush();
		applicationRoleEventSrc.fire(role);
	}

	public void createRole(ApplicationRole applicationRole) {
		moleculeEm.persist(applicationRole);
		moleculeEm.flush();
		applicationRoleEventSrc.fire(applicationRole);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		Role role = new Role(applicationRole.getKey());
		identityManager.add(role);
	}

	public void deleteRole(ApplicationRole applicationRole) {
		ApplicationRole role = moleculeEm.find(ApplicationRole.class, applicationRole.getId());
		moleculeEm.remove(role);
		moleculeEm.flush();
		applicationRoleEventSrc.fire(role);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		Role role_ = BasicModel.getRole(identityManager, applicationRole.getKey());
		identityManager.remove(role_);
	}
}
