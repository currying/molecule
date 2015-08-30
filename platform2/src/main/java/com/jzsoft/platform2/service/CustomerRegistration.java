package com.jzsoft.platform2.service;

import static org.picketlink.idm.model.basic.BasicModel.grantRole;

import java.util.Date;
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

import com.jzsoft.platform2.model.Customer;

@Stateless
public class CustomerRegistration {

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Inject
	private PartitionManager partitionManager;

	@Inject
	private Event<Customer> customerEventSrc;

	public void register(Customer customer) throws Exception {
		customer.setTime(new Date().getTime());

//		User user = new User(customer.getLoginName());
//		user.setEmail(customer.getEmail());
//		IdentityManager identityManager = this.partitionManager
//				.createIdentityManager();
//		identityManager.add(user);
//		identityManager.updateCredential(user,
//				new Password(customer.getPassword()));
//		Role maintenanceMan = BasicModel.getRole(identityManager, "customer");
//		RelationshipManager relationshipManager = this.partitionManager
//				.createRelationshipManager();
//		grantRole(relationshipManager, user, maintenanceMan);

		em.persist(customer);
		customerEventSrc.fire(customer);
		log.info("新增客户： " + customer.getCustomerName());
	}
}
