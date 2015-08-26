package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.query.IdentityQuery;
import org.picketlink.idm.query.IdentityQueryBuilder;

@ApplicationScoped
public class GroupRepository {
	@Inject
	private IdentityManager identityManager;

	public List<Group> findAllOrderedByName() {
		IdentityQueryBuilder queryBuilder = identityManager.getQueryBuilder();
		IdentityQuery<Group> query = queryBuilder.createIdentityQuery(Group.class);
		return query.getResultList();
	}
}
