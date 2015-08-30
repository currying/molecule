package com.jzsoft.platform2.data;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.json.JSONArray;

import com.jzsoft.platform2.model.Job;
import com.jzsoft.platform2.model.Job_;

@ApplicationScoped
public class JobRepository {

	@Inject
	private EntityManager em;

	public Job findById(String id) {
		return em.find(Job.class, id);
	}

	public List<Job> findByWorkTicketNumber(String workTicketNumber) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Job> criteria = cb.createQuery(Job.class);
		Root<Job> job = criteria.from(Job.class);
		criteria.select(job)
				.where(cb.equal(job.get("workTicketNumber"), workTicketNumber))
				.orderBy(cb.desc(job.get("startTime")));
		return em.createQuery(criteria).getResultList();
	}

	public List<Job> findAllOrderedByTime() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Job> criteria = cb.createQuery(Job.class);
		Root<Job> job = criteria.from(Job.class);
		criteria.select(job).orderBy(cb.desc(job.get("startTime")));
		return em.createQuery(criteria).getResultList();
	}

	public List<Job> findAllOrderedByTimeTop(int top) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Job> criteria = cb.createQuery(Job.class);
		Root<Job> job = criteria.from(Job.class);
		criteria.select(job).orderBy(cb.desc(job.get("startTime")));
		return em.createQuery(criteria).setMaxResults(top).getResultList();
	}

	public List<String> findJobImageById(String id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
		Root<Job> job = criteria.from(Job.class);
		criteria.multiselect(job.get(Job_.image).alias("image"));
		criteria.where(builder.equal(job.get(Job_.id), id));
		List<String> images = new ArrayList<String>();
		JSONArray jsArr = new JSONArray((String) em.createQuery(criteria)
				.getSingleResult().get("image"));
		for (int i = 0; i < jsArr.length(); i++) {
			images.add(jsArr.getString(i));
		}
		return images;
	}

	public String findJobAudioById(String id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
		Root<Job> job = criteria.from(Job.class);
		criteria.multiselect(job.get(Job_.audio).alias("audio"));
		criteria.where(builder.equal(job.get(Job_.id), id));
		return em.createQuery(criteria).getSingleResult().get("audio")
				.toString();
	}
}
