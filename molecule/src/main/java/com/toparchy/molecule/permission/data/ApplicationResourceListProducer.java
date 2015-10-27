package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.toparchy.molecule.permission.model.entity.ApplicationResource;

@ApplicationScoped
public class ApplicationResourceListProducer {
	@Inject
	private ApplicationResourceRepository applicationResourceRepository;
	@Produces
	@Named
	private List<ApplicationResource> applicationResources;

	public List<ApplicationResource> getApplicationResources() {
		return applicationResources;
	}

	public void onApplicationResourceRepositoryListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final ApplicationResource systemResource) {
		retrieveAllApplicationResourceRepository();
	}

	@PostConstruct
	public void retrieveAllApplicationResourceRepository() {
		applicationResources = applicationResourceRepository.findAll();
	}

}
