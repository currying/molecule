package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.molecule.permission.model.ApplicationRole;

@ApplicationScoped
public class ApplicationRoleRepository {
	@Inject
	private EntityManager em;

	public ApplicationRole findById(String id) {
		return em.find(ApplicationRole.class, id);
	}

	public List<ApplicationRole> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ApplicationRole> criteria = cb.createQuery(ApplicationRole.class);
		Root<ApplicationRole> applicationRole = criteria.from(ApplicationRole.class);
		criteria.select(applicationRole);
		return em.createQuery(criteria).getResultList();
	}
}
