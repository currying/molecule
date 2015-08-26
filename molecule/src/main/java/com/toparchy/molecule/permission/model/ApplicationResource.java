package com.toparchy.molecule.permission.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "SYS_APPLICATION_ROLE_ID")
	private ApplicationRole applicationRole;

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

	public ApplicationRole getApplicationRole() {
		return applicationRole;
	}

	public void setApplicationRole(ApplicationRole applicationRole) {
		this.applicationRole = applicationRole;
	}

}
