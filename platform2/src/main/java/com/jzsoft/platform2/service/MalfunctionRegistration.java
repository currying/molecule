package com.jzsoft.platform2.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jzsoft.platform2.model.Malfunction;

@Stateless
public class MalfunctionRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Malfunction> malfunctionEventSrc;

	public void register(Malfunction malfunction) throws Exception {
		em.persist(malfunction);
		malfunctionEventSrc.fire(malfunction);
		log.info("新增故障： " + malfunction.getId());
	}
}
