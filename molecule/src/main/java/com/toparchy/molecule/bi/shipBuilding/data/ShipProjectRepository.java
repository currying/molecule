package com.toparchy.molecule.bi.shipBuilding.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.molecule.bi.shipBuilding.model.ShipProject;
import com.toparchy.molecule.bi.shipBuilding.model.ShipYard;

@ApplicationScoped
public class ShipProjectRepository {
	@Inject
	private EntityManager em;

	public ShipProject findById(String id) {
		return em.find(ShipProject.class, id);
	}

	public List<ShipProject> findByShipYard(ShipYard shipYard) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ShipProject> criteria = cb.createQuery(ShipProject.class);
		Root<ShipProject> shipProject = criteria.from(ShipProject.class);
		criteria.select(shipProject).where(
				cb.equal(shipProject.get("shipYard"), shipYard));
		return em.createQuery(criteria).getResultList();
	}

	public List<ShipProject> findAllShipProject() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ShipProject> criteria = cb.createQuery(ShipProject.class);
		Root<ShipProject> shipProject = criteria.from(ShipProject.class);
		criteria.select(shipProject);
		return em.createQuery(criteria).getResultList();
	}
}
