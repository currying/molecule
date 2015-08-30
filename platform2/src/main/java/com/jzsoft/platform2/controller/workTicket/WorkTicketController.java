package com.jzsoft.platform2.controller.workTicket;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

import com.jzsoft.platform2.model.Malfunction;
import com.jzsoft.platform2.model.Member;
import com.jzsoft.platform2.model.WorkTicket;
import com.jzsoft.platform2.service.WorkTicketRegistration;

@Model
@ViewScoped
public class WorkTicketController implements Serializable {

	private static final long serialVersionUID = -5049127849873283173L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private WorkTicketRegistration workTicketRegistration;

	@Produces
	@Named
	private WorkTicket newWorkTicket;

	@Produces
	@Named
	private Member currentMember;

	@Produces
	@Named
	private Malfunction currentMalfunction;

	@Produces
	@Named
	private Set<Malfunction> selectMalfunctions = new HashSet<Malfunction>();

	@PostConstruct
	public void initNewWorkTicket() {
		newWorkTicket = new WorkTicket();
		Date now = new Date();
		Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		newWorkTicket.setWorkTicketNumber(formatter.format(now));
		newWorkTicket.setStartTime(now.getTime());
	}

	public void register() {
		try {
			newWorkTicket.setMalfunctions(selectMalfunctions);
			workTicketRegistration.register(newWorkTicket);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Increased!", "Dispatching successful");
			facesContext.addMessage(null, m);
			initNewWorkTicket();
			currentMember = new Member();
			currentMalfunction = new Malfunction();
			selectMalfunctions.clear();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
			newWorkTicket.setId(null);
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

	public void chooseMember() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 320);
		RequestContext.getCurrentInstance().openDialog("dialog/selectmember",
				options, null);
	}

	public void onMemberChosen(SelectEvent event) {
		currentMember = (Member) event.getObject();
		newWorkTicket.setMember(currentMember);
	}

	public void selectMemberFromDialog(Member member) {
		RequestContext.getCurrentInstance().closeDialog(member);
	}

	public void chooseMalfunction() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400);
		RequestContext.getCurrentInstance().openDialog(
				"dialog/selectmalfunction", options, null);
	}

	public void onMalfunctionChosen(SelectEvent event) {
		currentMalfunction = (Malfunction) event.getObject();
		currentMalfunction.setDispatchingMark(1);
		selectMalfunctions.add(currentMalfunction);
	}

	public void selectMalfunctionFromDialog(Malfunction selectMalfunction) {
		RequestContext.getCurrentInstance().closeDialog(selectMalfunction);
	}
}
