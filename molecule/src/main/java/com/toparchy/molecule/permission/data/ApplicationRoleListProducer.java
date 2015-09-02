package com.toparchy.molecule.permission.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.basic.Group;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.toparchy.molecule.permission.model.ApplicationResource;
import com.toparchy.molecule.permission.model.ApplicationRole;
import com.toparchy.molecule.permission.service.RoleResourceRegistration;

@Model
@ViewScoped
public class ApplicationRoleListProducer implements Serializable {

	private static final long serialVersionUID = 8927851599583647659L;

	@Inject
	private ApplicationRoleRepository applicationRoleRepository;
	@Inject
	private RoleResourceRegistration roleResourceRegistration;

	private List<ApplicationRole> applicationRoles;

	@Produces
	@Named
	private ApplicationRole currentApplicationRole;
	@Produces
	@Named
	private Set<ApplicationResource> currentApplicationResources;

	@Produces
	@Named
	public List<ApplicationRole> getApplicationRoles() {
		return applicationRoles;
	}

	public void onApplicationRoleRepositoryListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Group group) {
		retrieveAllApplicationRoleRepositorys();
	}

	@PostConstruct
	public void retrieveAllApplicationRoleRepositorys() {
		applicationRoles = applicationRoleRepository.findAll();
	}

	public ApplicationRole getCurrentApplicationRole() {
		return currentApplicationRole;
	}

	public void setCurrentApplicationRole(ApplicationRole currentApplicationRole) {
		this.currentApplicationRole = currentApplicationRole;
	}

	public Set<ApplicationResource> getCurrentApplicationResources() {
		return currentApplicationResources;
	}

	public void setCurrentApplicationResources(Set<ApplicationResource> currentApplicationResources) {
		this.currentApplicationResources = currentApplicationResources;
	}

	public void onRowSelect(SelectEvent event) {
		// FacesMessage msg = new FacesMessage("Role Selected",
		// ((ApplicationRole) event.getObject()).getId());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		currentApplicationRole = (ApplicationRole) event.getObject();
		currentApplicationResources = ((ApplicationRole) event.getObject()).getApplicationResources();
	}

	public void onRowUnselect(UnselectEvent event) {
		// FacesMessage msg = new FacesMessage("Role Unselected",
		// ((ApplicationRole) event.getObject()).getId());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void chooseResource() {
		RequestContext.getCurrentInstance().openDialog("selectApplicationResource");
	}

	public void onResourceChosen(SelectEvent event) {
		ApplicationResource resource = (ApplicationResource) event.getObject();
		System.out.println(currentApplicationRole + " 1111 " + resource);
		roleResourceRegistration.add(currentApplicationRole, resource);
	}
}
