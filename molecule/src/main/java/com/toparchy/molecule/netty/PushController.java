package com.toparchy.molecule.netty;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Model
@ViewScoped
public class PushController implements Serializable {
	private static final long serialVersionUID = -5895372970795488308L;
	@Produces
	@Named
	private PushMessageForm newPushMessageForm;
	@Inject
	private PushServiceProxy pushServiceProxy;

	@PostConstruct
	public void initNewPushMessageForm() {
		newPushMessageForm = new PushMessageForm();
	}

	public void create() {
		// 手动设定过滤信息
		Client clientsParams = new Client();
		clientsParams.setAppKey("apptest");
		newPushMessageForm.setClient(clientsParams);
		pushServiceProxy.push(newPushMessageForm);
		newPushMessageForm = new PushMessageForm();
	}
}
