package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.toparchy.molecule.permission.model.SystemResource;

@ApplicationScoped
public class ApplicationResourceListProducer {
	@Inject
	private ApplicationResourceRepository applicationResourceRepository;
	@Produces
	@Named
	private List<SystemResource> systemResources;

	public List<SystemResource> getApplicationResources() {
		return systemResources;
	}

	public void onApplicationResourceRepositoryListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final SystemResource systemResource) {
		retrieveAllApplicationResourceRepository();
	}

	@PostConstruct
	public void retrieveAllApplicationResourceRepository() {
		systemResources = applicationResourceRepository.findAll();
	}

}
