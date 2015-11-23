package com.toparchy.csic704.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CSIC704_EMPLOYEE")
@XmlRootElement
public class Employee implements Serializable {

	private static final long serialVersionUID = -8083051342213321502L;

	@Id
	@Column(name = "EMPLOYEE_ID", length = 50)
	@GeneratedValue(generator = "employee-uuid")
	@GenericGenerator(name = "employee-uuid", strategy = "uuid")
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	private DepartMent departMent;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "employee")
	@JsonIgnore
	private Set<AssessmentStructure> assessmentStructures;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DepartMent getDepartMent() {
		return departMent;
	}

	public void setDepartMent(DepartMent departMent) {
		this.departMent = departMent;
	}

	public Set<AssessmentStructure> getReviewStructures() {
		return assessmentStructures;
	}

	public void setReviewStructures(Set<AssessmentStructure> assessmentStructures) {
		this.assessmentStructures = assessmentStructures;
	}

}
