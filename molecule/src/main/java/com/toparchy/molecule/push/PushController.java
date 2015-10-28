package com.toparchy.molecule.push;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.toparchy.molecule.permission.annotations.P00000009;
import com.toparchy.molecule.permission.annotations.P00000010;
import com.toparchy.molecule.permission.model.Member;
import com.toparchy.molecule.push.baidu.BaiduPushService;
import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;

@Model
@ViewScoped
public class PushController implements Serializable {
	private static final long serialVersionUID = -6504012356407772961L;
	@Produces
	@Named
	private PushMessageForm pushMessageForm;
	@Inject
	private BaiduPushService pushService;
	@Produces
	private BaseDataWrapper baseDataWrapper;

	private Member currentMember;

	public Member getCurrentMember() {
		return currentMember;
	}

	public void setCurrentMember(Member currentMember) {
		this.currentMember = currentMember;
	}

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

	public void onRowSelect(SelectEvent event) {
		pushMessageForm.setChannelId(currentMember.getChannelId());
	}

	public void onRowUnselect(UnselectEvent event) {
		pushMessageForm.setChannelId("");
	}

	@P00000009
	public void pushMsgToDevic() throws PushClientException, PushServerException {
		if (immediate) {
			pushMessageForm.setSendTime(null);
		}
		if (pushMessageForm.getChannelId().equals("")) {
			baseDataWrapper = pushService.pushMsgToAll(pushMessageForm);
			return;
		}
		if (pushMessageForm.getChannelId().indexOf(',') == -1 && !pushMessageForm.getChannelId().equals("")) {
			baseDataWrapper = pushService.pushMsgToSingleDevic(pushMessageForm);
			return;
		}
	}

	@P00000010
	public void queryStatisticDevice() throws PushClientException, PushServerException {
		baseDataWrapper = pushService.queryStatisticDevice();
	}
}
