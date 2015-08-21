package com.jzsoft.platform2.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jzsoft.platform2.model.Equipment;

@Stateless
public class EquipmentRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Equipment> equipmentEventSrc;

	public void register(Equipment equipment) throws Exception {
		em.persist(equipment);
		equipmentEventSrc.fire(equipment);
		log.info("新增设备： " + equipment.getName());
	}
}