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
		applicationRole.addApplicationResource(applicationResource);
		em.merge(applicationRole);
		// applicationRoleEventSrc.fire(applicationRole);
	}

	public void remove(ApplicationRole applicationRole, ApplicationResource applicationResource) {
		applicationRole.removeApplicationResource(applicationResource);
		em.merge(applicationRole);
		// applicationRoleEventSrc.fire(applicationRole);
	}
}
