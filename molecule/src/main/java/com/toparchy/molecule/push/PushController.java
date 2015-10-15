package com.toparchy.molecule.push;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.toparchy.molecule.permission.annotations.P00000009;
import com.toparchy.molecule.permission.annotations.P00000010;
import com.toparchy.molecule.push.baidu.BaiduPushService;
import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;

@Model
@RequestScoped
public class PushController implements Serializable {
	private static final long serialVersionUID = -6504012356407772961L;
	@Produces
	@Named
	private PushMessageForm pushMessageForm;
	@Inject
	private BaiduPushService pushService;
	@Produces
	private BaseDataWrapper baseDataWrapper;

	private boolean immediate = true;

	public boolean isImmediate() {
		return immediate;
	}

	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}

	public BaseDataWrapper getBaseDataWrapper() {
		return baseDataWrapper;
	}

	public void setBaseDataWrapper(BaseDataWrapper baseDataWrapper) {
		this.baseDataWrapper = baseDataWrapper;
	}

	@PostConstruct
	public void initNewPushMessageForm() {
		pushMessageForm = new PushMessageForm();
	}

	@P00000009
	public void pushMsgToDevic() throws PushClientException, PushServerException {
		System.out.println(immediate);
		if (immediate) {
			pushMessageForm.setSendTime(null);
		}
		if (pushMessageForm.getChannelId().equals("")) {
			baseDataWrapper = pushService.pushMsgToAll(pushMessageForm);
		}
		if (pushMessageForm.getChannelId().indexOf(',') == -1 && !pushMessageForm.getChannelId().equals("")) {
			baseDataWrapper = pushService.pushMsgToSingleDevic(pushMessageForm);
		}
	}

	@P00000010
	public void queryStatisticDevice() throws PushClientException, PushServerException {
		baseDataWrapper = pushService.queryStatisticDevice();
	}
}
