package com.jzsoft.platform2.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jzsoft.platform2.model.MalfunctionType;

@ApplicationScoped
public class MalfunctionTypeRepository {

	@Inject
	private EntityManager em;

	public List<MalfunctionType> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MalfunctionType> criteria = cb
				.createQuery(MalfunctionType.class);
		Root<MalfunctionType> malfunctionType = criteria
				.from(MalfunctionType.class);
		criteria.select(malfunctionType);
		return em.createQuery(criteria).getResultList();
	}

}
