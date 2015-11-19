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
@Table(name = "CSIC704_DEPARTMENT")
@XmlRootElement
public class DepartMent implements Serializable {

	private static final long serialVersionUID = -4718430601470842625L;

	@Id
	@Column(name = "DEPARTMENT_ID", length = 50)
	@GeneratedValue(generator = "departMent-uuid")
	@GenericGenerator(name = "departMent-uuid", strategy = "uuid")
	private String id;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "departMentParent")
	@JsonIgnore
	private Set<DepartMent> departMentes;
	@ManyToOne(fetch = FetchType.EAGER)
	private DepartMent departMentParent;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "departMent")
	@JsonIgnore
	private Set<Employee> employees;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<DepartMent> getDepartMentes() {
		return departMentes;
	}

	public void setDepartMentes(Set<DepartMent> departMentes) {
		this.departMentes = departMentes;
	}

	public DepartMent getDepartMentParent() {
		return departMentParent;
	}

	public void setDepartMentParent(DepartMent departMentParent) {
		this.departMentParent = departMentParent;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
