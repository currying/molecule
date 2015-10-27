package com.toparchy.molecule.permission.controller;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;

import org.primefaces.context.RequestContext;

import com.toparchy.molecule.permission.model.entity.ApplicationResource;

@Model
@ViewScoped
public class ApplicationResourceController implements Serializable {
	private static final long serialVersionUID = 4811449229018947187L;

	public void selectResourceFromDialog(ApplicationResource applicationResource) {
		RequestContext.getCurrentInstance().closeDialog(applicationResource);
	}
}
