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
@Table(name = "JZ_BI_CLASSIFICATIONSOCIETY_")
@XmlRootElement
public class ClassificationSociety implements Serializable {

	private static final long serialVersionUID = 8652365988742908469L;
	@Id
	@Column(name = "CLASSIFICATIONSOCIETY_ID", length = 50)
	@GeneratedValue(generator = "classificationSociety-uuid")
	@GenericGenerator(name = "classificationSociety-uuid", strategy = "uuid")
	private String classificationSocietyId;
	@Column(name = "CLASSIFICATIONSOCIETY_NAME", length = 255)
	private String classificationSocietyName;
	@Column(name = "CLASSIFICATIONSOCIETY_ABBREVIATION", length = 50)
	private String classificationSocietyAbbreviation;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "classificationSociety")
	@JsonIgnore
	private Set<ShipProject> shipProjects;

	public ClassificationSociety() {
	}

	public String getClassificationSocietyId() {
		return classificationSocietyId;
	}

	public void setClassificationSocietyId(String classificationSocietyId) {
		this.classificationSocietyId = classificationSocietyId;
	}

	public String getClassificationSocietyName() {
		return classificationSocietyName;
	}

	public void setClassificationSocietyName(String classificationSocietyName) {
		this.classificationSocietyName = classificationSocietyName;
	}

	public String getClassificationSocietyAbbreviation() {
		return classificationSocietyAbbreviation;
	}

	public void setClassificationSocietyAbbreviation(
			String classificationSocietyAbbreviation) {
		this.classificationSocietyAbbreviation = classificationSocietyAbbreviation;
	}

	public Set<ShipProject> getShipProjects() {
		return shipProjects;
	}

	public void setShipProjects(Set<ShipProject> shipProjects) {
		this.shipProjects = shipProjects;
	}
}
