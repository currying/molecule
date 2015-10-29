package com.toparchy.molecule.permission.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.toparchy.molecule.permission.model.entity.MemberEntity;

@ApplicationScoped
public class MemberEntityRepository {
	@Inject
	private EntityManager moleculeEm;

	public MemberEntity findById(String id) {
		return moleculeEm.find(MemberEntity.class, id);
	}
}
