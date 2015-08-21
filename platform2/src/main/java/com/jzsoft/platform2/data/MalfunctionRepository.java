package com.jzsoft.platform2.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jzsoft.platform2.model.Malfunction;

@ApplicationScoped
public class MalfunctionRepository {
	@Inject
	private EntityManager em;

	public List<Malfunction> findByCustomer(String customer) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Malfunction> criteria = cb.createQuery(Malfunction.class);
		Root<Malfunction> malfunction = criteria.from(Malfunction.class);
		criteria.select(malfunction)
				.where(cb.equal(malfunction.get("customer"), customer))
				.orderBy(cb.desc(malfunction.get("time")));
		return em.createQuery(criteria).getResultList();
	}

	public List<Malfunction> findAllOrderedByTime() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Malfunction> criteria = cb.createQuery(Malfunction.class);
		Root<Malfunction> malfunction = criteria.from(Malfunction.class);
		criteria.select(malfunction)
				.where(cb.equal(malfunction.get("dispatchingMark"), 0))
				.orderBy(cb.desc(malfunction.get("time")));
		return em.createQuery(criteria).getResultList();
	}
}
