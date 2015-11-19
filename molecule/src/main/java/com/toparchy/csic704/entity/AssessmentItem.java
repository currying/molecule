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
@Table(name = "CSIC704_ASSESSMENTITEM")
@XmlRootElement
public class AssessmentItem implements Serializable {
	private static final long serialVersionUID = -900791143583606523L;
	@Id
	@Column(name = "ASSESSMENTITEM_ID", length = 50)
	@GeneratedValue(generator = "assessmentItem-uuid")
	@GenericGenerator(name = "assessmentItem-uuid", strategy = "uuid")
	private String id;

	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentProject assessmentProject1;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentParentItem")
	@JsonIgnore
	private Set<AssessmentItem> assessmentItems;

	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentItem assessmentParentItem;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentItem")
	@JsonIgnore
	private Set<ReviewScore> reviewScores;

	public AssessmentItem() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AssessmentProject getAssessmentProject1() {
		return assessmentProject1;
	}

	public void setAssessmentProject1(AssessmentProject assessmentProject1) {
		this.assessmentProject1 = assessmentProject1;
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

	public Set<ReviewScore> getReviewScores() {
		return reviewScores;
	}

	public void setReviewScores(Set<ReviewScore> reviewScores) {
		this.reviewScores = reviewScores;
	}

}
