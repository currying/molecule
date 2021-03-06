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
@Table(name = "ASSESSMENT_OBJECT")
@XmlRootElement
public class AssessmentObject implements Serializable {

	private static final long serialVersionUID = 3302460772890884264L;
	@Id
	@Column(name = "ID_", length = 50)
	@GeneratedValue(generator = "assessmentObject-uuid")
	@GenericGenerator(name = "assessmentObject-uuid", strategy = "uuid")
	private String id;
	@Column(name = "NAME_")
	private String name;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "assessmentObject")
	@JsonIgnore
	private Set<AssessmentTask> assessmentTasks;

	public AssessmentObject() {
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

	public Set<AssessmentTask> getAssessmentTasks() {
		return assessmentTasks;
	}

	public void setAssessmentTasks(Set<AssessmentTask> assessmentTasks) {
		this.assessmentTasks = assessmentTasks;
	}

}
