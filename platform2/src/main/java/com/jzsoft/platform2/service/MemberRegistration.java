package com.jzsoft.platform2.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

import com.jzsoft.platform2.model.Member;

import static org.picketlink.idm.model.basic.BasicModel.grantRole;

@Stateless
public class MemberRegistration {

	@Inject
	private Logger log;

	@Inject
	private PartitionManager partitionManager;

	@Inject
	private EntityManager em;

	@Inject
	private Event<Member> memberEventSrc;

	public void register(Member member) throws Exception {
		log.info("注册维修员： " + member.getName());
//		User user = new User(member.getLoginName());
//		user.setEmail(member.getEmail());
//		IdentityManager identityManager = this.partitionManager
//				.createIdentityManager();
//		identityManager.add(user);
//		identityManager.updateCredential(user,
//				new Password(member.getPassword()));
//		Role maintenanceMan = BasicModel.getRole(identityManager,
//				"maintenance-man");
//		RelationshipManager relationshipManager = this.partitionManager
//				.createRelationshipManager();
//		grantRole(relationshipManager, user, maintenanceMan);
		em.persist(member);
		memberEventSrc.fire(member);
	}
}
