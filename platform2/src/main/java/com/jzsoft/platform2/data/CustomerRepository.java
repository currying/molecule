package com.jzsoft.platform2.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jzsoft.platform2.model.Customer;

@ApplicationScoped
public class CustomerRepository {

	@Inject
	private EntityManager em;

	public Customer findById(String id) {
		return em.find(Customer.class, id);
	}

	public Customer findByPhoneNumber(String phoneNumber) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
		Root<Customer> customer = criteria.from(Customer.class);
		criteria.select(customer).where(
				cb.equal(customer.get("phoneNumber"), phoneNumber));
		return em.createQuery(criteria).getSingleResult();
	}

	public Customer findByName(String customerName) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
		Root<Customer> customer = criteria.from(Customer.class);
		criteria.select(customer).where(
				cb.equal(customer.get("customerName"), customerName));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<Customer> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
		Root<Customer> customer = criteria.from(Customer.class);
		criteria.select(customer).orderBy(cb.asc(customer.get("customerName")));
		return em.createQuery(criteria).getResultList();
	}
}
