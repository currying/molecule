package com.jzsoft.platform2.controller.equipment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.jzsoft.platform2.model.Customer;
import com.jzsoft.platform2.model.Equipment;
import com.jzsoft.platform2.model.EquipmentType;
import com.jzsoft.platform2.service.EquipmentRegistration;

@Model
@ViewScoped
public class EquipmentController implements Serializable {

	private static final long serialVersionUID = -1234144910350479960L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private EquipmentRegistration equipmentRegistration;

	@Produces
	@Named
	private Equipment newEquipment;

	@Produces
	@Named
	private EquipmentType currentEquipmentType;

	@Produces
	@Named
	private Customer selectEquipmentCustomer;

	@PostConstruct
	public void initNewEquipment() {
		newEquipment = new Equipment();
	}

	public void register() throws Exception {
		try {
			equipmentRegistration.register(newEquipment);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewEquipment();
			currentEquipmentType = new EquipmentType();
			selectEquipmentCustomer = new Customer();
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

	public void chooseEquipmentType() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 320);
		RequestContext.getCurrentInstance().openDialog(
				"dialog/selectequipmenttype", options, null);
	}

	public void onEquipmentTypeChosen(SelectEvent event) {
		currentEquipmentType = (EquipmentType) event.getObject();
		newEquipment.setEquipmentType(currentEquipmentType);
	}

	public void selectEquipmentTypeFromDialog(EquipmentType equipmentType) {
		RequestContext.getCurrentInstance().closeDialog(equipmentType);
	}

	public void chooseCustomer() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 320);
		RequestContext.getCurrentInstance().openDialog("dialog/selectcustomer",
				options, null);
	}

	public void onCustomerChosen(SelectEvent event) {
		selectEquipmentCustomer = (Customer) event.getObject();
		newEquipment.setCustomer(selectEquipmentCustomer);
	}

	public void selectCustomerFromDialog(Customer customer) {
		RequestContext.getCurrentInstance().closeDialog(customer);
	}

}
