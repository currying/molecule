package com.toparchy.molecule.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.model.IdentityType;
import org.picketlink.idm.query.IdentityQuery;
import org.picketlink.idm.query.IdentityQueryBuilder;

import com.toparchy.molecule.permission.model.Member;

@ApplicationScoped
public class MemberRepository {

	@Inject
	private PartitionManager partitionManager;

	public Member findById(String id) {
		IdentityQueryBuilder queryBuilder = partitionManager
				.createIdentityManager().getQueryBuilder();
		IdentityQuery<Member> query = queryBuilder
				.createIdentityQuery(Member.class);
		query.where(queryBuilder.equal(IdentityType.ID, id));
		return query.getResultList().get(0);
	}

	public List<Member> findAllOrderedByName() {
		IdentityQueryBuilder queryBuilder = partitionManager
				.createIdentityManager().getQueryBuilder();
		IdentityQuery<Member> query = queryBuilder
				.createIdentityQuery(Member.class);
		return query.getResultList();
	}
}
