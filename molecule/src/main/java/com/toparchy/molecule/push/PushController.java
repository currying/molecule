package com.toparchy.molecule.push;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.toparchy.molecule.push.baidu.BaiduPushService;
import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;

@Model
@RequestScoped
public class PushController implements Serializable {
	private static final long serialVersionUID = -6504012356407772961L;
	@Produces
	@Named
	private PushMessageForm newPushMessageForm;
	@Inject
	private BaiduPushService pushService;
	@Produces
	private BaseDataWrapper baseDataWrapper;

	public BaseDataWrapper getBaseDataWrapper() {
		return baseDataWrapper;
	}

	public void setBaseDataWrapper(BaseDataWrapper baseDataWrapper) {
		this.baseDataWrapper = baseDataWrapper;
	}

	@PostConstruct
	public void initNewPushMessageForm() {
		newPushMessageForm = new PushMessageForm();
	}

	public void push() throws PushClientException, PushServerException {
		baseDataWrapper = pushService.pushMsgToSingleDevic(newPushMessageForm);
	}
}
