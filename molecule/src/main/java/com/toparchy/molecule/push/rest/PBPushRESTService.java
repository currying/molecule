package com.toparchy.molecule.push.rest;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.toparchy.molecule.push.BaseDataWrapper;
import com.toparchy.molecule.push.PushMessageForm;
import com.toparchy.molecule.push.baidu.BaiduPushService;
import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;

@Path("/external/push")
public class PBPushRESTService {

	@Inject
	private BaiduPushService pushService;
	private BaseDataWrapper baseDataWrapper;

	public BaseDataWrapper getBaseDataWrapper() {
		return baseDataWrapper;
	}

	public void setBaseDataWrapper(BaseDataWrapper baseDataWrapper) {
		this.baseDataWrapper = baseDataWrapper;
	}

	@POST
	@Path("/pgd")
	public Response pushPGD(PushMessageForm pushMessageForm) throws PushClientException, PushServerException {
		if (pushMessageForm.getChannelId().equals("null") || pushMessageForm.getChannelId().equals("")) {
			baseDataWrapper = pushService.pushMsgToAll(pushMessageForm);
			return Response.ok().entity(baseDataWrapper).type(MediaType.APPLICATION_JSON_TYPE).build();
		}
		if (pushMessageForm.getChannelId().indexOf(',') == -1 && !pushMessageForm.getChannelId().equals("")
				&& !pushMessageForm.getChannelId().equals("null")) {
			baseDataWrapper = pushService.pushMsgToSingleDevic(pushMessageForm);
			return Response.ok().entity(baseDataWrapper).type(MediaType.APPLICATION_JSON_TYPE).build();
		}
		return Response.ok().entity(baseDataWrapper).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}
