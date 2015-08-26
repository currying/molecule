package com.toparchy.molecule.bi.shipBuilding.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JZ_BI_SHIPYARD_")
@XmlRootElement
public class ShipYard implements Serializable {

	private static final long serialVersionUID = -2647245926851671503L;
	@Id
	@Column(name = "SHIPYARD_ID", length = 50)
	@GeneratedValue(generator = "shipYard-uuid")
	@GenericGenerator(name = "shipYard-uuid", strategy = "uuid")
	private String shipYardId;
	@Column(name = "SHIPYARD_NAME", length = 255)
	private String shipYardName;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "shipYard")
	@JsonIgnore
	private Set<ShipProject> shipProjects;

	public ShipYard() {
	}

	public String getShipYardId() {
		return shipYardId;
	}

	public void setShipYardId(String shipYardId) {
		this.shipYardId = shipYardId;
	}

	public String getShipYardName() {
		return shipYardName;
	}

	public void setShipYardName(String shipYardName) {
		this.shipYardName = shipYardName;
	}

	public Set<ShipProject> getShipXMs() {
		return shipProjects;
	}

	public void setShipProjects(Set<ShipProject> shipProjects) {
		this.shipProjects = shipProjects;
	}
}
