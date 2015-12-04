package com.toparchy.csic704.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ASSESSMENT_SCORE")
@XmlRootElement
public class AssessmentScore implements Serializable {

	private static final long serialVersionUID = 7989928144071437453L;
	@Id
	@Column(name = "ID_", length = 50)
	@GeneratedValue(generator = "assessmentScore-uuid")
	@GenericGenerator(name = "assessmentScore-uuid", strategy = "uuid")
	private String id;
	@Column(name = "NAME_")
	private String name;
	@Column(name = "SCORE_")
	private float score;// 打分
	@Column(name = "WEIGHTING_")
	private float weighting;// 细项权重，包括一级二级
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSESSMENT_ITEM_ID")
	private AssessmentItem assessmentItem;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSESSMENT_GROUP_ID")
	private AssessmentGroup assessmentGroup;

	public AssessmentScore() {
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

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public float getWeighting() {
		return weighting;
	}

	public void setWeighting(float weighting) {
		this.weighting = weighting;
	}

	public AssessmentItem getAssessmentItem() {
		return assessmentItem;
	}

	public void setAssessmentItem(AssessmentItem assessmentItem) {
		this.assessmentItem = assessmentItem;
	}

	public AssessmentGroup getAssessmentGroup() {
		return assessmentGroup;
	}

	public void setAssessmentGroup(AssessmentGroup assessmentGroup) {
		this.assessmentGroup = assessmentGroup;
	}

}
