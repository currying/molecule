package com.jzsoft.platform2.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jzsoft.platform2.model.WorkTicket;

@Stateless
public class WorkTicketRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<WorkTicket> workTicketEventSrc;

	public void register(WorkTicket workTicket) throws Exception {
		em.merge(workTicket);
		log.info("提交派工单： " + workTicket.getWorkTicketNumber());
		workTicketEventSrc.fire(workTicket);
	}

	public void update(WorkTicket workTicket) {
		log.info("更新派工单： " + workTicket.getWorkTicketNumber());
		em.merge(workTicket);
		workTicketEventSrc.fire(workTicket);
	}
}
