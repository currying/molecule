package com.jzsoft.platform2.controller.member;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.render.ResponseStateManager;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.model.Member;
import com.jzsoft.platform2.service.MemberRegistration;

@Model
public class MemberController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private MemberRegistration memberRegistration;

	@Produces
	@Named
	private Member newMember;

	private String token = "";

	@PostConstruct
	public void initNewMember() {
		newMember = new Member();
		token = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("javax.faces.Token");
	}

	private boolean isPostback(FacesContext context) {
		return context.getExternalContext().getRequestParameterMap()
				.containsKey(ResponseStateManager.VIEW_STATE_PARAM);
	}

	public void register() throws Exception {
		System.out.println(isPostback(FacesContext.getCurrentInstance()));
		try {
			memberRegistration.register(newMember);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewMember();
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					errorMessage, "Registration unsuccessful");
			facesContext.addMessage(null, m);
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

}
