package com.toparchy.molecule.permission.data;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.GroupMembership;
import org.picketlink.idm.query.IdentityQuery;
import org.picketlink.idm.query.IdentityQueryBuilder;
import org.picketlink.idm.query.RelationshipQuery;

import com.toparchy.molecule.permission.model.Member;

@ApplicationScoped
public class GroupRepository {
	@Inject
	private IdentityManager identityManager;
	@Inject
	private RelationshipManager relationshipManager;

	public List<Group> findAllOrderedByName() {
		IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
		IdentityQuery<Group> query = queryBuilder.createIdentityQuery(Group.class);
		return query.getResultList();
	}

	public List<Account> findGroupAccount(Group group) {
		RelationshipQuery<GroupMembership> query = this.relationshipManager
				.createRelationshipQuery(GroupMembership.class);

		query.setParameter(GroupMembership.MEMBER, group);

		List<Account> accounts = new ArrayList<Account>();

		for (GroupMembership groupMembership : query.getResultList()) {
			accounts.add(groupMembership.getMember());
		}

		return accounts;
	}
}
