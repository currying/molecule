package com.jzsoft.platform2.controller.malfunctionType;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.model.MalfunctionType;
import com.jzsoft.platform2.service.MalfunctionTypeRegistration;

@Model
@ViewScoped
public class MalfunctionTypeController implements Serializable {

	private static final long serialVersionUID = -273259693472805365L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private MalfunctionTypeRegistration malfunctionTypeRegistration;

	@Produces
	@Named
	private MalfunctionType newMalfunctionType;

	@PostConstruct
	public void initNewMalfunctionType() {
		newMalfunctionType = new MalfunctionType();
	}

	public void register() throws Exception {
		try {
			malfunctionTypeRegistration.register(newMalfunctionType);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewMalfunctionType();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}

	private String getRootErrorMessage(Exception e) {
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			return errorMessage;
		}

		Throwable t = e;
		while (t != null) {
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		return errorMessage;
	}
}
