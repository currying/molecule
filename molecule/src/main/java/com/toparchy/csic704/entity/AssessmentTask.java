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
@Table(name = "ASSESSMENTTASK")
@XmlRootElement
public class AssessmentTask implements Serializable {

	private static final long serialVersionUID = 4683989156948356208L;
	@Id
	@Column(name = "ASSESSMENTTASK_ID", length = 50)
	@GeneratedValue(generator = "assessmentTask-uuid")
	@GenericGenerator(name = "assessmentTask-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentObject assessmentObject;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentTask1")
	@JsonIgnore
	private Set<AssessmentItem> assessmentItems;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentTask2")
	@JsonIgnore
	private Set<AssessmentStructure> assessmentStructures;

	public AssessmentTask() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AssessmentObject getAssessmentObject() {
		return assessmentObject;
	}

	public void setAssessmentObject(AssessmentObject assessmentObject) {
		this.assessmentObject = assessmentObject;
	}

	public Set<AssessmentItem> getAssessmentItems() {
		return assessmentItems;
	}

	public void setAssessmentItems(Set<AssessmentItem> assessmentItems) {
		this.assessmentItems = assessmentItems;
	}

	public Set<AssessmentStructure> getReviewStructures() {
		return assessmentStructures;
	}

	public void setReviewStructures(Set<AssessmentStructure> assessmentStructures) {
		this.assessmentStructures = assessmentStructures;
	}

}
