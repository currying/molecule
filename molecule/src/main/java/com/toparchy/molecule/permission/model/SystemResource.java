package com.toparchy.molecule.permission.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SYS_SYSTEM_RESOURCE")
@XmlRootElement
public class SystemResource implements Serializable {

	private static final long serialVersionUID = 4440709995178566201L;
	@Id
	@Column(name = "SYS_APPLICATION_RESOURCE_ID", length = 50)
	@GeneratedValue(generator = "systemResource-uuid")
	@GenericGenerator(name = "systemResource-uuid", strategy = "uuid")
	private String id;
	@Column(name = "SYS_APPLICATION_RESOURCE_KEY", length = 255)
	private String key;
	@Column(name = "SYS_APPLICATION_RESOURCE_NAME", length = 255)
	private String name;
	@Column(name = "SYS_APPLICATION_RESOURCE_TYPE", length = 255)
	private String type;
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "systemResources")
	@JsonIgnore
	@OrderBy("key ASC")
	private Set<SystemRoleEntity> systemRoleEntities = new HashSet<SystemRoleEntity>();

	public SystemResource() {
	}

	public SystemResource(String key, String name, String type) {
		this.key = key;
		this.name = name;
		this.type = type;
	}

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

	public Set<SystemRoleEntity> getSystemRoleEntities() {
		return systemRoleEntities;
	}

	public void setSystemRoleEntities(Set<SystemRoleEntity> systemRoleEntities) {
		this.systemRoleEntities = systemRoleEntities;
	}
}
