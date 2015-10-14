package com.toparchy.molecule.push.baidu.client;

import java.util.concurrent.Future;

import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;
import com.toparchy.molecule.push.baidu.model.PushMsgToSingleDeviceRequest;
import com.toparchy.molecule.push.baidu.model.PushMsgToSingleDeviceResponse;

public interface BaiduPushAsync {

	Future<PushMsgToSingleDeviceResponse> pushMsgToSingleDeviceAsync(
			PushMsgToSingleDeviceRequest request)
			throws PushClientException, PushServerException;

}
