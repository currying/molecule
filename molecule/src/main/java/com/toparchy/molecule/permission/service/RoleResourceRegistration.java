package com.toparchy.molecule.permission.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.toparchy.molecule.permission.model.ApplicationResource;
import com.toparchy.molecule.permission.model.ApplicationRole;

@Stateless
public class RoleResourceRegistration {
	@Inject
	private EntityManager em;

	@Inject
	private Event<ApplicationRole> applicationRoleEventSrc;

	public void add(ApplicationRole applicationRole, ApplicationResource applicationResource) {
		ApplicationRole role = em.find(ApplicationRole.class, applicationRole.getId());
		ApplicationResource resource = em.find(ApplicationResource.class, applicationResource.getId());
		role.addApplicationResource(resource);
		// applicationRole.addApplicationResource(applicationResource);
		em.merge(role);
		em.flush();
		// applicationRoleEventSrc.fire(role);
	}

	public void remove(ApplicationRole applicationRole, ApplicationResource applicationResource) {
		ApplicationRole role = em.find(ApplicationRole.class, applicationRole.getId());
		ApplicationResource resource = em.find(ApplicationResource.class, applicationResource.getId());
		role.removeApplicationResource(resource);
		// applicationRole.removeApplicationResource(applicationResource);
		em.merge(role);
		em.flush();
		// applicationRoleEventSrc.fire(applicationRole);
	}
}
