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
@Table(name = "JZ_BI_SHIPOWNER_")
@XmlRootElement
public class ShipOwner implements Serializable {

	private static final long serialVersionUID = -2647245926851671503L;
	@Id
	@Column(name = "SHIPOWNER_ID", length = 50)
	@GeneratedValue(generator = "shipOwner-uuid")
	@GenericGenerator(name = "shipOwner-uuid", strategy = "uuid")
	private String shipOwnerId;
	@Column(name = "SHIPOWNER_NAME", length = 255)
	private String shipOwnerName;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "shipOwner")
	@JsonIgnore
	private Set<ShipProject> shipProjects;

	public ShipOwner() {
	}

	public String getShipOwnerId() {
		return shipOwnerId;
	}

	public void setShipOwnerId(String shipOwnerId) {
		this.shipOwnerId = shipOwnerId;
	}

	public String getShipOwnerName() {
		return shipOwnerName;
	}

	public void setShipOwnerName(String shipOwnerName) {
		this.shipOwnerName = shipOwnerName;
	}

	public Set<ShipProject> getShipProjects() {
		return shipProjects;
	}

	public void setShipProjects(Set<ShipProject> shipProjects) {
		this.shipProjects = shipProjects;
	}
}
