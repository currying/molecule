package com.jzsoft.platform2.controller.equipmentType;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.model.EquipmentType;
import com.jzsoft.platform2.service.EquipmentTypeRegistration;

@Model
public class EquipmentTypeController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private EquipmentTypeRegistration equipmentTypeRegistration;

	@Produces
	@Named
	private EquipmentType newEquipmentType;

	@PostConstruct
	public void initNewEquipmentType() {
		newEquipmentType = new EquipmentType();
	}

	public void register() throws Exception {
		try {
			equipmentTypeRegistration.register(newEquipmentType);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewEquipmentType();
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
