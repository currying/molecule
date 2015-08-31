package com.toparchy.molecule.permission.data;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.IdentityType;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.GroupMembership;
import org.picketlink.idm.query.IdentityQuery;
import org.picketlink.idm.query.IdentityQueryBuilder;
import org.picketlink.idm.query.RelationshipQuery;

import com.toparchy.molecule.permission.model.Member;

@ApplicationScoped
public class MemberRepository {

	@Inject
	private IdentityManager identityManager;

	@Inject
	private RelationshipManager relationshipManager;

	public Member findById(String id) {
		IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
		IdentityQuery<Member> query = queryBuilder.createIdentityQuery(Member.class);
		query.where(queryBuilder.equal(IdentityType.ID, id));
		return query.getResultList().get(0);
	}

	public List<Member> findAllOrderedByName() {
		IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
		IdentityQuery<Member> query = queryBuilder.createIdentityQuery(Member.class);
		return query.getResultList();
	}

	public List<Group> findMemberGroup(Member member) {
		RelationshipQuery<GroupMembership> query = this.relationshipManager
				.createRelationshipQuery(GroupMembership.class);

		query.setParameter(GroupMembership.MEMBER, member);

		List<Group> groups = new ArrayList<Group>();

		for (GroupMembership groupMembership : query.getResultList()) {
			groups.add((Group) groupMembership.getGroup());
		}

		return groups;
	}
}
