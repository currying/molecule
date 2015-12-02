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
@Table(name = "ASSESSMENT_ITEM")
@XmlRootElement
public class AssessmentItem implements Serializable {
	private static final long serialVersionUID = -900791143583606523L;
	@Id
	@Column(name = "ID_", length = 50)
	@GeneratedValue(generator = "assessmentItem-uuid")
	@GenericGenerator(name = "assessmentItem-uuid", strategy = "uuid")
	private String id;
	@Column(name = "NAME_")
	private String name;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSESSMENT_TASK_ID_")
	private AssessmentTask assessmentTask1;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentParentItem")
	@JsonIgnore
	private Set<AssessmentItem> assessmentItems;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PID_")
	private AssessmentItem assessmentParentItem;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentItem")
	@JsonIgnore
	private Set<AssessmentScore> assessmentScores;

	public AssessmentItem() {
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

	public AssessmentTask getAssessmentTask1() {
		return assessmentTask1;
	}

	public void setAssessmentTask1(AssessmentTask assessmentTask1) {
		this.assessmentTask1 = assessmentTask1;
	}

	public Set<AssessmentScore> getAssessmentScores() {
		return assessmentScores;
	}

	public void setAssessmentScores(Set<AssessmentScore> assessmentScores) {
		this.assessmentScores = assessmentScores;
	}

	public Set<AssessmentItem> getAssessmentItems() {
		return assessmentItems;
	}

	public void setAssessmentItems(Set<AssessmentItem> assessmentItems) {
		this.assessmentItems = assessmentItems;
	}

	public AssessmentItem getAssessmentParentItem() {
		return assessmentParentItem;
	}

	public void setAssessmentParentItem(AssessmentItem assessmentParentItem) {
		this.assessmentParentItem = assessmentParentItem;
	}

	public Set<AssessmentScore> getReviewScores() {
		return assessmentScores;
	}

	public void setReviewScores(Set<AssessmentScore> assessmentScores) {
		this.assessmentScores = assessmentScores;
	}

}
