package com.jzsoft.platform2.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.jzsoft.platform2.model.EquipmentType;

@Stateless
public class EquipmentTypeRegistration {
	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private Event<EquipmentType> equipmentTypeEventSrc;

	public void register(EquipmentType equipmentType) throws Exception {
		em.persist(equipmentType);
		equipmentTypeEventSrc.fire(equipmentType);
		log.info("新增设备类型： " + equipmentType.getName());
	}
}
