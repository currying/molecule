package com.toparchy.molecule.push.baidu;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;

import com.toparchy.molecule.push.BaseDataWrapper;
import com.toparchy.molecule.push.PushConfig;
import com.toparchy.molecule.push.PushMessageForm;
import com.toparchy.molecule.push.PushServiceProxy;
import com.toparchy.molecule.push.baidu.auth.PushKeyPair;
import com.toparchy.molecule.push.baidu.client.BaiduPushClient;
import com.toparchy.molecule.push.baidu.constants.BaiduPushConstants;
import com.toparchy.molecule.push.baidu.core.log.YunLogEvent;
import com.toparchy.molecule.push.baidu.core.log.YunLogHandler;
import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;
import com.toparchy.molecule.push.baidu.model.PushMsgToSingleDeviceRequest;
import com.toparchy.molecule.push.baidu.model.PushMsgToSingleDeviceResponse;

import net.sf.json.JSONObject;

@RequestScoped
public class BaiduPushService implements PushServiceProxy<BaseDataWrapper> {

	@Override
	public BaseDataWrapper pushMsgToSingleDevic(PushMessageForm pushMessageForm)
			throws PushClientException, PushServerException {
		BaseDataWrapper json = new BaseDataWrapper();
		PushKeyPair pair = new PushKeyPair(PushConfig.BAIDU_ANDROID_APIKEY, PushConfig.BAIDU_ANDROID_SECRETKEY);
		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			JSONObject notification = new JSONObject();
			notification.put("title", pushMessageForm.getTitle());
			notification.put("description", pushMessageForm.getContent());
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 1);
			notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);
			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(pushMessageForm.getChannelId()).addMsgExpires(new Integer(3600)). // message有效时间
					addMessageType(pushMessageForm.getMsgType()).// 1：通知,0:透传消息.
																	// 默认为0
																	// 注：IOS只有通知.
			addMessage(notification.toString()).addDeviceType(pushMessageForm.getDeviceType());// deviceType
			// =>
			// 3:android,
			// 4:ios
			PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("msgId", response.getMsgId());
			responseMap.put("sendTime", response.getSendTime());
			json.setSuccess(true);
			json.setData(responseMap);
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			json.setSuccess(false);
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				json.setSuccess(false);
				Map<String, Object> responseMap = new HashMap<String, Object>();
				responseMap.put("request_id", e.getRequestId());
				responseMap.put("error_code", e.getErrorCode());
				responseMap.put("error_msg", e.getErrorMsg());
				json.setData(responseMap);
			}
		}
		return json;
	}

}
