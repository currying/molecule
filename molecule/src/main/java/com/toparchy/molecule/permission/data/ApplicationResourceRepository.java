package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.molecule.permission.model.SystemResource;

@ApplicationScoped
public class ApplicationResourceRepository {
//	@PersistenceContext(unitName = "molecule")
	@Inject
	private EntityManager moleculeEm;

	public SystemResource findByKey(String key) {
		CriteriaBuilder cb = moleculeEm.getCriteriaBuilder();
		CriteriaQuery<SystemResource> criteria = cb.createQuery(SystemResource.class);
		Root<SystemResource> systemResource = criteria.from(SystemResource.class);
		criteria.select(systemResource).where(cb.equal(systemResource.get("key"), key));
		return moleculeEm.createQuery(criteria).getSingleResult();
	}

	public List<SystemResource> findAll() {
		CriteriaBuilder cb = moleculeEm.getCriteriaBuilder();
		CriteriaQuery<SystemResource> criteria = cb.createQuery(SystemResource.class);
		Root<SystemResource> systemResource = criteria.from(SystemResource.class);
		criteria.select(systemResource);
		return moleculeEm.createQuery(criteria).getResultList();
	}
}
