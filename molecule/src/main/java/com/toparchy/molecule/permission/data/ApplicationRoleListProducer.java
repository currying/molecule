package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.basic.Role;

import com.toparchy.molecule.permission.model.ApplicationRole;

@RequestScoped
public class ApplicationRoleListProducer {

	@Inject
	private ApplicationRoleRepository applicationRoleRepository;

	private List<ApplicationRole> applicationRoles;

	@Produces
	@Named
	public List<ApplicationRole> getApplicationRoles() {
		return applicationRoles;
	}

	public void onApplicationRoleRepositoryListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Role role) {
		retrieveAllApplicationRoleRepositorys();
	}

	@PostConstruct
	public void retrieveAllApplicationRoleRepositorys() {
		applicationRoles = applicationRoleRepository.findAll();
	}

}
