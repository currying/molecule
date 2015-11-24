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
@Table(name = "ASSESSMENTSCORE")
@XmlRootElement
public class AssessmentScore implements Serializable {

	private static final long serialVersionUID = 7989928144071437453L;
	@Id
	@Column(name = "ASSESSMENTSCORE_ID", length = 50)
	@GeneratedValue(generator = "assessmentScore-uuid")
	@GenericGenerator(name = "assessmentScore-uuid", strategy = "uuid")
	private String id;
	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentItem assessmentItem;
	@ManyToOne(fetch = FetchType.EAGER)
	private AssessmentStructure assessmentStructure;

	public AssessmentScore() {
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

	public AssessmentStructure getAssessmentStructure() {
		return assessmentStructure;
	}

	public void setAssessmentStructure(AssessmentStructure assessmentStructure) {
		this.assessmentStructure = assessmentStructure;
	}

}
