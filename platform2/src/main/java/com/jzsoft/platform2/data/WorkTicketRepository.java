package com.jzsoft.platform2.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jzsoft.platform2.model.WorkTicket;

@ApplicationScoped
public class WorkTicketRepository {

	@Inject
	private EntityManager em;

	public WorkTicket findById(String id) {
		return em.find(WorkTicket.class, id);
	}

	public WorkTicket findByWorkTicketNumber(String workTicketNumber) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<WorkTicket> criteria = cb.createQuery(WorkTicket.class);
		Root<WorkTicket> workTicket = criteria.from(WorkTicket.class);
		criteria.select(workTicket)
				.where(cb.equal(workTicket.get("workTicketNumber"),
						workTicketNumber))
				.orderBy(cb.desc(workTicket.get("startTime")));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<WorkTicket> findAllOrderedByStartTime() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<WorkTicket> criteria = cb.createQuery(WorkTicket.class);
		Root<WorkTicket> workTicket = criteria.from(WorkTicket.class);
		criteria.select(workTicket).orderBy(
				cb.desc(workTicket.get("startTime")));
		return em.createQuery(criteria).getResultList();
	}

	public List<WorkTicket> findAllOrderedByStartTimeTop(int top) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<WorkTicket> criteria = cb.createQuery(WorkTicket.class);
		Root<WorkTicket> workTicket = criteria.from(WorkTicket.class);
		criteria.select(workTicket).orderBy(
				cb.desc(workTicket.get("startTime")));
		return em.createQuery(criteria).setMaxResults(top).getResultList();
	}

	public List<WorkTicket> findAllOrderedByPhoneNumber(String phoneNumber) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<WorkTicket> criteria = cb.createQuery(WorkTicket.class);
		Root<WorkTicket> workTicket = criteria.from(WorkTicket.class);
		criteria.select(workTicket).where(
				cb.equal(workTicket.get("member").get("phoneNumber"),
						phoneNumber));
		return em.createQuery(criteria).getResultList();
	}
}
