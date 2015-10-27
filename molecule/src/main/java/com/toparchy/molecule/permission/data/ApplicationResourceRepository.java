package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.molecule.permission.model.entity.ApplicationResource;

@ApplicationScoped
public class ApplicationResourceRepository {
	// @PersistenceContext(unitName = "molecule")
	@Inject
	private EntityManager moleculeEm;

	public ApplicationResource findByKey(String key) {
		CriteriaBuilder cb = moleculeEm.getCriteriaBuilder();
		CriteriaQuery<ApplicationResource> criteria = cb.createQuery(ApplicationResource.class);
		Root<ApplicationResource> applicationResource = criteria.from(ApplicationResource.class);
		criteria.select(applicationResource).where(cb.equal(applicationResource.get("key"), key));
		return moleculeEm.createQuery(criteria).getSingleResult();
	}

	public List<ApplicationResource> findAll() {
		CriteriaBuilder cb = moleculeEm.getCriteriaBuilder();
		CriteriaQuery<ApplicationResource> criteria = cb.createQuery(ApplicationResource.class);
		Root<ApplicationResource> applicationResource = criteria.from(ApplicationResource.class);
		criteria.select(applicationResource);
		return moleculeEm.createQuery(criteria).getResultList();
	}
}
