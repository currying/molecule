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
@Table(name = "CSIC704_REVIEWSTRUCTURE")
@XmlRootElement
public class AssessmentStructure implements Serializable {

	private static final long serialVersionUID = 3025673345452963270L;
	@Id
	@Column(name = "REVIEWSTRUCTURE_ID", length = 50)
	@GeneratedValue(generator = "reviewStructure-uuid")
	@GenericGenerator(name = "reviewStructure-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentProject assessmentProject2;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "reviewParentStructure")
	@JsonIgnore
	private Set<AssessmentStructure> assessmentStructures;
	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentStructure reviewParentStructure;

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

	public AssessmentProject getAssessmentProject2() {
		return assessmentProject2;
	}

	public void setAssessmentProject2(AssessmentProject assessmentProject2) {
		this.assessmentProject2 = assessmentProject2;
	}

	public Set<AssessmentStructure> getReviewStructures() {
		return assessmentStructures;
	}

	public void setReviewStructures(Set<AssessmentStructure> assessmentStructures) {
		this.assessmentStructures = assessmentStructures;
	}

	public AssessmentStructure getReviewParentStructure() {
		return reviewParentStructure;
	}

	public void setReviewParentStructure(AssessmentStructure reviewParentStructure) {
		this.reviewParentStructure = reviewParentStructure;
	}

	public Set<AssessmentScore> getReviewScores() {
		return assessmentScores;
	}

	public void setReviewScores(Set<AssessmentScore> assessmentScores) {
		this.assessmentScores = assessmentScores;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
