package com.toparchy.molecule.permission.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;

import com.toparchy.molecule.permission.model.ApplicationResource;
import com.toparchy.molecule.permission.model.ApplicationRole;

@Stateless
public class RoleResourceRegistration {
	@Inject
	private EntityManager em;
	@Inject
	private PartitionManager partitionManager;

	@Inject
	private Event<ApplicationRole> applicationRoleEventSrc;

	public void add(ApplicationRole applicationRole, ApplicationResource applicationResource) {
		ApplicationRole role = em.find(ApplicationRole.class, applicationRole.getId());
		ApplicationResource resource = em.find(ApplicationResource.class, applicationResource.getId());
		role.addApplicationResource(resource);
		// applicationRole.addApplicationResource(applicationResource);
		em.merge(role);
		em.flush();
		applicationRoleEventSrc.fire(role);
	}

	public void remove(ApplicationRole applicationRole, ApplicationResource applicationResource) {
		ApplicationRole role = em.find(ApplicationRole.class, applicationRole.getId());
		ApplicationResource resource = em.find(ApplicationResource.class, applicationResource.getId());
		role.removeApplicationResource(resource);
		// applicationRole.removeApplicationResource(applicationResource);
		em.merge(role);
		em.flush();
		applicationRoleEventSrc.fire(role);
	}

	public void createRole(ApplicationRole applicationRole) {
		em.persist(applicationRole);
		em.flush();
		applicationRoleEventSrc.fire(applicationRole);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		Role role = new Role(applicationRole.getKey());
		identityManager.add(role);
	}

	public void deleteRole(ApplicationRole applicationRole) {
		ApplicationRole role = em.find(ApplicationRole.class, applicationRole.getId());
		em.remove(role);
		em.flush();
		applicationRoleEventSrc.fire(role);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		Role role_ = BasicModel.getRole(identityManager, applicationRole.getKey());
		identityManager.remove(role_);
	}
}
