package com.toparchy.molecule.bi.shipBuilding.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.toparchy.molecule.bi.shipBuilding.model.ShipProject;

@Model
@ViewScoped
public class ShipProjectListProducer implements Serializable {

	private static final long serialVersionUID = 1647662140126911289L;

	@Inject
	private ShipProjectRepository shipProjectRepository;

	private List<ShipProject> shipProjects;

	private List<SelectItem> shipProjectIdentifiers;

	private String selectShipProjectId;

	private ShipProject selectShipProject;

	private List<ShipProject> seriesShipProjects;

	public String getSelectShipProjectId() {
		return selectShipProjectId;
	}

	public void setSelectShipProjectId(String selectShipProjectId) {
		this.selectShipProjectId = selectShipProjectId;
	}

	@Produces
	@Named
	public ShipProject getSelectShipProject() {
		return selectShipProject;
	}

	public void setSelectShipProject(ShipProject selectShipProject) {
		this.selectShipProject = selectShipProject;
	}

	@Produces
	@Named
	public List<ShipProject> getSeriesShipProjects() {
		return seriesShipProjects;
	}

	@Produces
	@Named
	public List<ShipProject> getShipProjects() {
		return shipProjects;
	}

	@Produces
	@Named
	public List<SelectItem> getShipProjectIdentifiers() {
		return shipProjectIdentifiers;
	}

	@PostConstruct
	public void retrieveAllShipProject() {
		shipProjects = shipProjectRepository.findAllShipProject();
		retrieveAllShipProjectIdentifier();
	}

	public void onShipProjectChange() {
		if (selectShipProjectId != null && !selectShipProjectId.equals("")) {
			setSelectShipProject(shipProjectRepository
					.findById(selectShipProjectId));
			seriesShipProjects = shipProjectRepository
					.findByShipYard(selectShipProject.getShipYard());
		}
	}

	public void retrieveAllShipProjectIdentifier() {
		shipProjectIdentifiers = new ArrayList<SelectItem>();
		for (ShipProject shipProject : shipProjects) {
			shipProjectIdentifiers
					.add(new SelectItem(shipProject.getShipProjectId(),
							shipProject.getShipProjectIdentifier()));
		}
	}
}
