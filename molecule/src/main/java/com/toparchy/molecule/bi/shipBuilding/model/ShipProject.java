package com.toparchy.molecule.bi.shipBuilding.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JZ_BI_SHIPPROJECT_", uniqueConstraints = @UniqueConstraint(columnNames = { "SHIPPROJECT_IDENTIFIER" }))
@XmlRootElement
public class ShipProject implements Serializable {

	private static final long serialVersionUID = -422527223406777547L;
	@Id
	@Column(name = "SHIPPROJECT_ID", length = 50)
	@GeneratedValue(generator = "shipProject-uuid")
	@GenericGenerator(name = "shipProject-uuid", strategy = "uuid")
	private String shipProjectId;

	@Column(name = "SHIPPROJECT_NAME", length = 255)
	private String shipProjectName;
	@Column(name = "SHIPPROJECT_IDENTIFIER", length = 50)
	private String shipProjectIdentifier;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLASSIFICATIONSOCIETY_ID")
	private ClassificationSociety classificationSociety;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPYARD_ID")
	private ShipYard shipYard;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPOWNER_ID")
	private ShipOwner shipOwner;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPTYPE_ID")
	private ShipType shipType;
	// @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,
	// mappedBy = "shipProject")
	// @JsonIgnore
	// private Set<ShipProjectTime> shipProjectTimes;
	@OrderBy(clause = "sort asc")
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "shipProject")
	@JsonIgnore
	private Set<ShipProjectPlan> shipProjectPlans;

	public ShipProject() {
	}

	public String getShipProjectId() {
		return shipProjectId;
	}

	public void setShipProjectId(String shipProjectId) {
		this.shipProjectId = shipProjectId;
	}

	public String getShipProjectName() {
		return shipProjectName;
	}

	public void setShipProjectName(String shipProjectName) {
		this.shipProjectName = shipProjectName;
	}

	public String getShipProjectIdentifier() {
		return shipProjectIdentifier;
	}

	public void setShipProjectIdentifier(String shipProjectIdentifier) {
		this.shipProjectIdentifier = shipProjectIdentifier;
	}

	public ClassificationSociety getClassificationSociety() {
		return classificationSociety;
	}

	public void setClassificationSociety(
			ClassificationSociety classificationSociety) {
		this.classificationSociety = classificationSociety;
	}

	public ShipYard getShipYard() {
		return shipYard;
	}

	public void setShipYard(ShipYard shipYard) {
		this.shipYard = shipYard;
	}

	public ShipOwner getShipOwner() {
		return shipOwner;
	}

	public void setShipOwner(ShipOwner shipOwner) {
		this.shipOwner = shipOwner;
	}

	public ShipType getShipType() {
		return shipType;
	}

	public void setShipType(ShipType shipType) {
		this.shipType = shipType;
	}

	//
	// public Set<ShipProjectTime> getShipProjectTimes() {
	// return shipProjectTimes;
	// }
	//
	// public void setShipProjectTimes(Set<ShipProjectTime> shipProjectTimes) {
	// this.shipProjectTimes = shipProjectTimes;
	// }

	public Set<ShipProjectPlan> getShipProjectPlans() {
		return shipProjectPlans;
	}

	public void setShipProjectPlans(Set<ShipProjectPlan> shipProjectPlans) {
		this.shipProjectPlans = shipProjectPlans;
	}

}
