package com.toparchy.molecule.permission.data;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.toparchy.molecule.permission.model.ApplicationResource;

@Model
@ViewScoped
public class ApplicationResourceListProducer implements Serializable {
	private static final long serialVersionUID = 4811449229018947187L;
	@Inject
	private ApplicationResourceRepository applicationResourceRepository;
	@Produces
	@Named
	private List<ApplicationResource> applicationResources;

	public List<ApplicationResource> getApplicationResources() {
		return applicationResources;
	}

	@PostConstruct
	public void retrieveAllApplicationResourceRepository() {
		applicationResources = applicationResourceRepository.findAll();
	}

	public void selectResourceFromDialog(ApplicationResource applicationResource) {
		RequestContext.getCurrentInstance().closeDialog(applicationResource);
	}
}
