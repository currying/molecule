package com.toparchy.molecule.permission.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.toparchy.molecule.permission.model.ApplicationResource;
import com.toparchy.molecule.permission.model.ApplicationRole;

@Stateless
public class RoleResourceRegistration {
	@Inject
	private EntityManager em;

	public void add(ApplicationRole applicationRole, ApplicationResource applicationResource) {
		applicationRole.addApplicationResource(applicationResource);
		em.persist(applicationRole);
	}
}
