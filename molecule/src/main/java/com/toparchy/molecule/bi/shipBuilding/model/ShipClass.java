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
@Table(name = "JZ_BI_SHIPCLASS_")
@XmlRootElement
public class ShipClass implements Serializable {

	private static final long serialVersionUID = -6153501498124503693L;
	@Id
	@Column(name = "SHIPCLASS_ID", length = 50)
	@GeneratedValue(generator = "shipClass-uuid")
	@GenericGenerator(name = "shipClass-uuid", strategy = "uuid")
	private String shipClassId;
	@Column(name = "SHIPCLASS_NAME", length = 255)
	private String shipClassName;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "shipClass")
	@JsonIgnore
	private Set<ShipType> shipTypes;

	public ShipClass() {
	}

	public String getShipClassId() {
		return shipClassId;
	}

	public void setShipClassId(String shipClassId) {
		this.shipClassId = shipClassId;
	}

	public String getShipClassName() {
		return shipClassName;
	}

	public void setShipClassName(String shipClassName) {
		this.shipClassName = shipClassName;
	}

	public Set<ShipType> getShipTypes() {
		return shipTypes;
	}

	public void setShipTypes(Set<ShipType> shipTypes) {
		this.shipTypes = shipTypes;
	}

}
