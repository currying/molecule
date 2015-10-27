package com.toparchy.molecule.permission.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;

import org.picketlink.idm.jpa.annotations.AttributeValue;
import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;

@IdentityManaged(SystemRole.class)
@Entity
public class SystemRoleEntity extends IdentityTypeEntity {

	private static final long serialVersionUID = 5075914963929493129L;

	@AttributeValue
	private String name;

	@AttributeValue
	private String aliasName;

	@ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@OrderBy("key ASC")
	private Set<SystemResource> systemResources = new HashSet<SystemResource>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Set<SystemResource> getApplicationResources() {
		return systemResources;
	}

	public void addApplicationResource(SystemResource systemResource) {
		systemResources.add(systemResource);
	}

	public void removeApplicationResource(SystemResource systemResource) {
		systemResources.remove(systemResource);
	}

	public void setApplicationResources(Set<SystemResource> systemResources) {
		this.systemResources = systemResources;
	}
}
