package com.toparchy.molecule.permission.model.entity;
//package com.toparchy.molecule.permission.model;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ManyToMany;
//import javax.persistence.OrderBy;
//import javax.persistence.Table;
//
//import org.picketlink.idm.jpa.annotations.AttributeValue;
//import org.picketlink.idm.jpa.annotations.RelationshipClass;
//import org.picketlink.idm.jpa.annotations.entity.IdentityManaged;
//import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;
//
//import com.toparchy.molecule.permission.model.basic.Role;
//
//@IdentityManaged(Role.class)
//@Entity
//@Table(name = "SYS_SYSTEM_ROLE")
//public class SystemRoleEntity extends IdentityTypeEntity {
//
//	private static final long serialVersionUID = 5075914963929493129L;
//
//	@AttributeValue
//	private String name;
//
//	@AttributeValue
//	private String aliasName;
//
//	// @ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch =
//	// FetchType.EAGER)
//	// @OrderBy("key ASC")
//	// private Set<SystemResource> systemResources = new
//	// HashSet<SystemResource>();
//	@RelationshipClass
//	private String className;
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getAliasName() {
//		return aliasName;
//	}
//
//	public void setAliasName(String aliasName) {
//		this.aliasName = aliasName;
//	}
//
//	public String getClassName() {
//		return className;
//	}
//
//	public void setClassName(String className) {
//		this.className = className;
//	}
//
//	// public Set<SystemResource> getApplicationResources() {
//	// return systemResources;
//	// }
//	//
//	// public void addApplicationResource(SystemResource systemResource) {
//	// systemResources.add(systemResource);
//	// }
//	//
//	// public void removeApplicationResource(SystemResource systemResource) {
//	// systemResources.remove(systemResource);
//	// }
//	//
//	// public void setApplicationResources(Set<SystemResource> systemResources)
//	// {
//	// this.systemResources = systemResources;
//	// }
//}
