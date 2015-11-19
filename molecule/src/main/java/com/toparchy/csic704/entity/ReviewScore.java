package com.toparchy.csic704.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CSIC704_REVIEWSCORE")
@XmlRootElement
public class ReviewScore implements Serializable {

	private static final long serialVersionUID = 7989928144071437453L;
	@Id
	@Column(name = "REVIEWSCORE_ID", length = 50)
	@GeneratedValue(generator = "reviewScore-uuid")
	@GenericGenerator(name = "reviewScore-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentItem assessmentItem;
	@ManyToOne(fetch = FetchType.EAGER)
	private ReviewStructure reviewStructure;

	public ReviewScore() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AssessmentItem getAssessmentItem() {
		return assessmentItem;
	}

	public void setAssessmentItem(AssessmentItem assessmentItem) {
		this.assessmentItem = assessmentItem;
	}

	public ReviewStructure getReviewStructure() {
		return reviewStructure;
	}

	public void setReviewStructure(ReviewStructure reviewStructure) {
		this.reviewStructure = reviewStructure;
	}

}
