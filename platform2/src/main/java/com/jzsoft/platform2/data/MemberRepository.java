package com.jzsoft.platform2.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jzsoft.platform2.model.Member;

@ApplicationScoped
public class MemberRepository {

	@Inject
	private EntityManager em;

	public Member findById(String id) {
		return em.find(Member.class, id);
	}

	public Member findByEmail(String email) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> member = criteria.from(Member.class);
		criteria.select(member).where(cb.equal(member.get("email"), email));
		return em.createQuery(criteria).getSingleResult();
	}

	public Member findByPhoneNumber(String phoneNumber) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> member = criteria.from(Member.class);
		criteria.select(member).where(
				cb.equal(member.get("phoneNumber"), phoneNumber));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<Member> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
		Root<Member> member = criteria.from(Member.class);
		criteria.select(member).orderBy(cb.asc(member.get("name")));
		return em.createQuery(criteria).getResultList();
	}
}
