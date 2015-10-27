package com.toparchy.molecule.permission.model.basic;

import org.picketlink.idm.model.IdentityType;
import org.picketlink.idm.model.Relationship;
import org.picketlink.idm.query.RelationshipQueryParameter;

public class GroupRole extends Grant implements Relationship {

	private static final long serialVersionUID = 2844617870858266637L;

	public static final RelationshipQueryParameter GROUP = RELATIONSHIP_QUERY_ATTRIBUTE.byName("group");

	private Group group;

	public GroupRole() {
		super();
	}

	public GroupRole(IdentityType assignee, Group group, Role role) {
		super(assignee, role);
		this.group = group;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
