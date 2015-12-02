package com.toparchy.csic704.entity;

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
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ASSESSMENT_STRUCTURE")
@XmlRootElement
public class AssessmentStructure implements Serializable {

	private static final long serialVersionUID = 3025673345452963270L;
	@Id
	@Column(name = "ID_", length = 50)
	@GeneratedValue(generator = "assessmentStructure-uuid")
	@GenericGenerator(name = "assessmentStructure-uuid", strategy = "uuid")
	private String id;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "WEIGHTING_")
	private float weighting;// 默认权重
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSESSMENT_TASK_ID")
	private AssessmentTask assessmentTask2;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentParentStructure")
	@JsonIgnore
	private Set<AssessmentStructure> assessmentStructures;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PID_")
	private AssessmentStructure assessmentParentStructure;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentStructure")
	@JsonIgnore
	private Set<AssessmentScore> assessmentScores;

	public AssessmentStructure() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeighting() {
		return weighting;
	}

	public void setWeighting(float weighting) {
		this.weighting = weighting;
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

}
