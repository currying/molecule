package com.jzsoft.platform2.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jzsoft.platform2.model.Job;

@Stateless
public class JobRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Job> jobEventSrc;

	public void register(Job job) throws Exception {
		log.info("提交工作： " + job.getId());
		em.persist(job);
		jobEventSrc.fire(job);
	}

	public void update(Job job) {
		log.info("更新工作： " + job.getId());
		em.merge(job);
		jobEventSrc.fire(job);
	}
}
