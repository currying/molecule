package com.toparchy.molecule.permission.controller;

import java.io.Serializable;
import java.util.Set;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.toparchy.molecule.permission.model.ApplicationResource;
import com.toparchy.molecule.permission.model.ApplicationRole;
import com.toparchy.molecule.permission.service.RoleResourceRegistration;

@Model
@ViewScoped
public class ApplicationRoleController implements Serializable {

	private static final long serialVersionUID = -7279682200727128738L;
	@Inject
	private RoleResourceRegistration roleResourceRegistration;
	private boolean disabled = true;
	@Produces
	@Named
	private ApplicationRole selectApplicationRole;
	@Produces
	@Named
	private Set<ApplicationResource> currentApplicationResources;

	public ApplicationRole getSelectApplicationRole() {
		return selectApplicationRole;
	}

	public void setSelectApplicationRole(ApplicationRole selectApplicationRole) {
		this.selectApplicationRole = selectApplicationRole;
	}

	public Set<ApplicationResource> getCurrentApplicationResources() {
		return currentApplicationResources;
	}

	public void setCurrentApplicationResources(Set<ApplicationResource> currentApplicationResources) {
		this.currentApplicationResources = currentApplicationResources;
	}

	public void onRowSelect(SelectEvent event) {
		selectApplicationRole = (ApplicationRole) event.getObject();
		currentApplicationResources = selectApplicationRole.getApplicationResources();
		if (selectApplicationRole != null)
			disabled = false;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void onRowUnselect(UnselectEvent event) {
	}

	public void chooseResource() {
		RequestContext.getCurrentInstance().openDialog("selectApplicationResource");
	}

	public void onResourceChosen(SelectEvent event) {
		ApplicationResource resource = (ApplicationResource) event.getObject();
		roleResourceRegistration.add(selectApplicationRole, resource);
	}

	public void onApplicationResourceListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final ApplicationResource applicationResource) {
		currentApplicationResources = selectApplicationRole.getApplicationResources();
		currentApplicationResources.add(applicationResource);
	}
}
