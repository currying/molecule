package com.jzsoft.platform2.controller.job;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.model.Job;
import com.jzsoft.platform2.service.JobRegistration;

@Model
public class JobController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private JobRegistration jobRegistration;

	@Produces
	@Named
	private Job newJob;

	@PostConstruct
	public void initNewJob() {
		newJob = new Job();
	}

	public void register() throws Exception {
		try {
			jobRegistration.register(newJob);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewJob();
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
