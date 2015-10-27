package com.toparchy.molecule.permission.model.basic;

import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.annotation.AttributeProperty;
import org.picketlink.idm.model.annotation.IdentityStereotype;
import org.picketlink.idm.model.annotation.InheritsPrivileges;
import org.picketlink.idm.model.annotation.StereotypeProperty;
import org.picketlink.idm.model.annotation.Unique;
import org.picketlink.idm.query.QueryParameter;

import static org.picketlink.idm.model.annotation.StereotypeProperty.Property.IDENTITY_GROUP_NAME;

@IdentityStereotype(IdentityStereotype.Stereotype.GROUP)
public class Group extends AbstractIdentityType {

	private static final long serialVersionUID = -3553832607918448916L;

	public static final QueryParameter NAME = QUERY_ATTRIBUTE.byName("name");

	public static final QueryParameter ALIASNAME = QUERY_ATTRIBUTE.byName("aliasName");

	public static final QueryParameter PATH = QUERY_ATTRIBUTE.byName("path");

	public static final QueryParameter PARENT = QUERY_ATTRIBUTE.byName("parentGroup");

	public static final String PATH_SEPARATOR = "/";

	private String name;
	private String aliasName;
	private Group parentGroup;
	private String path;

	public Group() {

	}

	public Group(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Error creating Group - name cannot be null or empty");
		}

		this.name = name;
		this.path = buildPath(this);
	}

	public Group(String name, Group parentGroup) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Error creating Group - name cannot be null or empty");
		}

		this.name = name;
		this.parentGroup = parentGroup;

		this.path = buildPath(this);
	}

	@AttributeProperty
	@StereotypeProperty(IDENTITY_GROUP_NAME)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@AttributeProperty
	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	@AttributeProperty
	@Unique
	public String getPath() {
		this.path = buildPath(this);
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@InheritsPrivileges
	@AttributeProperty
	public Group getParentGroup() {
		return this.parentGroup;
	}

	@AttributeProperty
	public void setParentGroup(Group group) {
		this.parentGroup = group;
	}

	private String buildPath(Group group) {
		String name = PATH_SEPARATOR + group.getName();

		if (group.getParentGroup() != null) {
			name = buildPath(group.getParentGroup()) + name;
		}

		return name;
	}
}
