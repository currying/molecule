package com.jzsoft.platform2.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jzsoft.platform2.model.MalfunctionType;

@Stateless
public class MalfunctionTypeRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<MalfunctionType> malfunctionTypeEventSrc;

	public void register(MalfunctionType malfunctionType) throws Exception {
		em.persist(malfunctionType);
		malfunctionTypeEventSrc.fire(malfunctionType);
		log.info("新增故障类型： " + malfunctionType.getId());
	}
}
