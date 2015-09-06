package com.toparchy.molecule.permission.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_APPLICATION_RESOURCE")
@XmlRootElement
public class ApplicationResource implements Serializable {

	private static final long serialVersionUID = 4440709995178566201L;
	@Id
	@Column(name = "SYS_APPLICATION_RESOURCE_ID", length = 50)
	@GeneratedValue(generator = "applicationresource-uuid")
	@GenericGenerator(name = "applicationresource-uuid", strategy = "uuid")
	private String id;
	@Column(name = "SYS_APPLICATION_RESOURCE_KEY", length = 255)
	private String key;
	@Column(name = "SYS_APPLICATION_RESOURCE_NAME", length = 255)
	private String name;
	@Column(name = "SYS_APPLICATION_RESOURCE_TYPE", length = 255)
	private String type;
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Set<ApplicationRole> applicationRoles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<ApplicationRole> getApplicationRoles() {
		return applicationRoles;
	}

	public void addApplicationRole(ApplicationRole applicationRole) {
		applicationRoles.add(applicationRole);
	}

	public void setApplicationRoles(Set<ApplicationRole> applicationRoles) {
		this.applicationRoles = applicationRoles;
	}

}
