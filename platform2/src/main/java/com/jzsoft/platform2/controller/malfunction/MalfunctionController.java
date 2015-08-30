package com.jzsoft.platform2.controller.malfunction;

import java.io.Serializable;
import java.util.Date;
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
import com.jzsoft.platform2.model.Malfunction;
import com.jzsoft.platform2.model.MalfunctionType;
import com.jzsoft.platform2.service.MalfunctionRegistration;

@Model
@ViewScoped
public class MalfunctionController implements Serializable {

	private static final long serialVersionUID = 6458544800258709920L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private MalfunctionRegistration malfunctionRegistration;

	@Produces
	@Named
	private Malfunction newMalfunction;

	@Produces
	@Named
	private Customer currentCustomer;

	@Produces
	@Named
	private MalfunctionType currentMalfunctionType;

	@PostConstruct
	public void initNewMalfunction() {
		newMalfunction = new Malfunction();
		newMalfunction.setTime(new Date().getTime());
	}

	public void register() throws Exception {
		try {
			malfunctionRegistration.register(newMalfunction);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewMalfunction();
			currentCustomer = new Customer();
			currentMalfunctionType = new MalfunctionType();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			newMalfunction.setId(null);
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
		currentCustomer = (Customer) event.getObject();
		newMalfunction.setCustomer(currentCustomer);
	}

	public void selectCustomerFromDialog(Customer customer) {
		RequestContext.getCurrentInstance().closeDialog(customer);
	}

	public void chooseMalfunctionType() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 320);
		RequestContext.getCurrentInstance().openDialog(
				"dialog/selectmalfunctiontype", options, null);
	}

	public void onMalfunctionTypeChosen(SelectEvent event) {
		currentMalfunctionType = (MalfunctionType) event.getObject();
		newMalfunction.setMalfunctionType(currentMalfunctionType);
	}

	public void selectMalfunctionTypeFromDialog(MalfunctionType malfunctionType) {
		RequestContext.getCurrentInstance().closeDialog(malfunctionType);
	}

}
