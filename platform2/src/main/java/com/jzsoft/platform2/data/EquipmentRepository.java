package com.jzsoft.platform2.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jzsoft.platform2.model.Equipment;

@ApplicationScoped
public class EquipmentRepository {

	@Inject
	private EntityManager em;

	public Equipment findById(String id) {
		return em.find(Equipment.class, id);
	}

	public List<Equipment> findAllOrderedByProductionDate() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Equipment> criteria = cb.createQuery(Equipment.class);
		Root<Equipment> equipment = criteria.from(Equipment.class);
		criteria.select(equipment).orderBy(cb.desc(equipment.get("productionDate")));
		return em.createQuery(criteria).getResultList();
	}

}