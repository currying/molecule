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
@Table(name = "ASSESSMENTSTRUCTURE")
@XmlRootElement
public class AssessmentStructure implements Serializable {

	private static final long serialVersionUID = 3025673345452963270L;
	@Id
	@Column(name = "ASSESSMENTSTRUCTURE_ID", length = 50)
	@GeneratedValue(generator = "assessmentStructure-uuid")
	@GenericGenerator(name = "assessmentStructure-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentTask assessmentTask2;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentParentStructure")
	@JsonIgnore
	private Set<AssessmentStructure> assessmentStructures;
	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentStructure assessmentParentStructure;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentStructure")
	@JsonIgnore
	private Set<AssessmentScore> assessmentScores;

	@ManyToOne(fetch = FetchType.EAGER)
	private Employee employee;

	public AssessmentStructure() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AssessmentTask getAssessmentTask2() {
		return assessmentTask2;
	}

	public void setAssessmentTask2(AssessmentTask assessmentTask2) {
		this.assessmentTask2 = assessmentTask2;
	}

	public Set<AssessmentStructure> getAssessmentStructures() {
		return assessmentStructures;
	}

	public void setAssessmentStructures(Set<AssessmentStructure> assessmentStructures) {
		this.assessmentStructures = assessmentStructures;
	}

	public Set<AssessmentScore> getAssessmentScores() {
		return assessmentScores;
	}

	public void setAssessmentScores(Set<AssessmentScore> assessmentScores) {
		this.assessmentScores = assessmentScores;
	}

	public AssessmentStructure getAssessmentParentStructure() {
		return assessmentParentStructure;
	}

	public void setAssessmentParentStructure(AssessmentStructure assessmentParentStructure) {
		this.assessmentParentStructure = assessmentParentStructure;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
