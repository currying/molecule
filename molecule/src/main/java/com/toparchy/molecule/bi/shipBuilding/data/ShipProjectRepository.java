package com.toparchy.molecule.bi.shipBuilding.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.molecule.bi.shipBuilding.model.ShipProject;
import com.toparchy.molecule.bi.shipBuilding.model.ShipYard;

@ApplicationScoped
public class ShipProjectRepository {
	@PersistenceContext(unitName = "bi")
//	@PersistenceContext(unitName = "bi")
	private EntityManager biEm;

	public ShipProject findById(String id) {
		return biEm.find(ShipProject.class, id);
	}

	public List<ShipProject> findByShipYard(ShipYard shipYard) {
		CriteriaBuilder cb = biEm.getCriteriaBuilder();
		CriteriaQuery<ShipProject> criteria = cb.createQuery(ShipProject.class);
		Root<ShipProject> shipProject = criteria.from(ShipProject.class);
		criteria.select(shipProject).where(cb.equal(shipProject.get("shipYard"), shipYard));
		return biEm.createQuery(criteria).getResultList();
	}

	public List<ShipProject> findAllShipProject() {
		CriteriaBuilder cb = biEm.getCriteriaBuilder();
		CriteriaQuery<ShipProject> criteria = cb.createQuery(ShipProject.class);
		Root<ShipProject> shipProject = criteria.from(ShipProject.class);
		criteria.select(shipProject);
		return biEm.createQuery(criteria).getResultList();
	}
}
