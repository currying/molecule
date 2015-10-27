package com.toparchy.molecule.permission.controller;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.toparchy.molecule.permission.model.SystemResource;

@Model
public class RoleResourceView {
	public void chooseResource() {
		RequestContext.getCurrentInstance().openDialog("selectApplicationResource");
	}

	public void onResourceChosen(SelectEvent event) {
		SystemResource resource = (SystemResource) event.getObject();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Resource Selected",
				"Id:" + resource.getId());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
