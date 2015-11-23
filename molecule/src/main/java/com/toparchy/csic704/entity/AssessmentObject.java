package com.toparchy.csic704.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CSIC704_ASSESSMENTORG")
@XmlRootElement
public class AssessmentObject implements Serializable {

	private static final long serialVersionUID = 3302460772890884264L;
	@Id
	@Column(name = "ASSESSMENTORG_ID", length = 50)
	@GeneratedValue(generator = "assessmentOrg-uuid")
	@GenericGenerator(name = "assessmentOrg-uuid", strategy = "uuid")
	private String id;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentObject")
	@JsonIgnore
	private Set<AssessmentProject> assessmentProjectes;

	public AssessmentObject() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<AssessmentProject> getAssessmentProjectes() {
		return assessmentProjectes;
	}

	public void setAssessmentProjectes(Set<AssessmentProject> assessmentProjectes) {
		this.assessmentProjectes = assessmentProjectes;
	}

}
