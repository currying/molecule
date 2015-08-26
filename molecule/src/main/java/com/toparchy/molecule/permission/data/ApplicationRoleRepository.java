package com.toparchy.molecule.permission.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.toparchy.molecule.permission.model.ApplicationRole;

@ApplicationScoped
public class ApplicationRoleRepository {
	@Inject
	private EntityManager em;

	public ApplicationRole findById(String id) {
		return em.find(ApplicationRole.class, id);
	}
}
