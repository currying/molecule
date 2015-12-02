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
@Table(name = "EMPLOYEE")
@XmlRootElement
public class Employee implements Serializable {

	private static final long serialVersionUID = -8083051342213321502L;

	@Id
	@Column(name = "ID_", length = 50)
	@GeneratedValue(generator = "employee-uuid")
	@GenericGenerator(name = "employee-uuid", strategy = "uuid")
	private String id;
	@Column(name = "NAME_")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PID_")
	private DepartMent departMent;

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

	public DepartMent getDepartMent() {
		return departMent;
	}

	public void setDepartMent(DepartMent departMent) {
		this.departMent = departMent;
	}

}
