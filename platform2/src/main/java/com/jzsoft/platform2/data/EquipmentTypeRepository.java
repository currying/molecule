package com.jzsoft.platform2.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.jzsoft.platform2.model.EquipmentType;

@ApplicationScoped
public class EquipmentTypeRepository {

	@Inject
	private EntityManager em;

	public EquipmentType findById(String id) {
		return em.find(EquipmentType.class, id);
	}

	public List<EquipmentType> findAllOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<EquipmentType> criteria = cb
				.createQuery(EquipmentType.class);
		Root<EquipmentType> equipmentType = criteria.from(EquipmentType.class);
		criteria.select(equipmentType).orderBy(
				cb.asc(equipmentType.get("name")));
		return em.createQuery(criteria).getResultList();
	}

}
