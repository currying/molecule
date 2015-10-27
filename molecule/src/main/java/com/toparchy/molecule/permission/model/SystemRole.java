//package com.toparchy.molecule.permission.model;
//
//import org.picketlink.idm.model.annotation.AttributeProperty;
//import org.picketlink.idm.model.basic.Role;
//import org.picketlink.idm.query.QueryParameter;
//
//public class SystemRole extends Role {
//
//	private static final long serialVersionUID = -68706831305208228L;
//	public static final QueryParameter ALIASNAME = QUERY_ATTRIBUTE.byName("aliasName");
//
//	public SystemRole() {
//	}
//
//	public SystemRole(String name, String aliasName) {
//		super(name);
//		this.aliasName = aliasName;
//	}
//
//	private String aliasName;
//
//	@AttributeProperty
//	public String getAliasName() {
//		return aliasName;
//	}
//
//	public void setAliasName(String aliasName) {
//		this.aliasName = aliasName;
//	}
//
//}
