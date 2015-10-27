package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.molecule.permission.model.entity.ApplicationRole;

@ApplicationScoped
public class ApplicationRoleRepository {
	// @PersistenceContext(unitName = "molecule")
	@Inject
	private EntityManager moleculeEm;

	public ApplicationRole findById(String id) {
		return moleculeEm.find(ApplicationRole.class, id);
	}

	public ApplicationRole findByKey(String key) {
		CriteriaBuilder cb = moleculeEm.getCriteriaBuilder();
		CriteriaQuery<ApplicationRole> criteria = cb.createQuery(ApplicationRole.class);
		Root<ApplicationRole> applicationRole = criteria.from(ApplicationRole.class);
		criteria.select(applicationRole).where(cb.equal(applicationRole.get("key"), key));
		return moleculeEm.createQuery(criteria).getSingleResult();
	}

	public List<ApplicationRole> findAll() {
		CriteriaBuilder cb = moleculeEm.getCriteriaBuilder();
		CriteriaQuery<ApplicationRole> criteria = cb.createQuery(ApplicationRole.class);
		Root<ApplicationRole> applicationRole = criteria.from(ApplicationRole.class);
		criteria.select(applicationRole);
		return moleculeEm.createQuery(criteria).getResultList();
	}
}
