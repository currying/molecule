package com.toparchy.molecule.push.baidu;

import java.util.HashMap;
import java.util.List;
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
import com.toparchy.molecule.push.baidu.model.KeyValueForDevice;
import com.toparchy.molecule.push.baidu.model.PushMsgToAllRequest;
import com.toparchy.molecule.push.baidu.model.PushMsgToAllResponse;
import com.toparchy.molecule.push.baidu.model.PushMsgToSingleDeviceRequest;
import com.toparchy.molecule.push.baidu.model.PushMsgToSingleDeviceResponse;
import com.toparchy.molecule.push.baidu.model.QueryStatisticDeviceRequest;
import com.toparchy.molecule.push.baidu.model.QueryStatisticDeviceResponse;

import net.sf.json.JSONObject;

@RequestScoped
public class BaiduPushService implements PushServiceProxy<BaseDataWrapper> {

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
			notification.put("notification_basic_style", 0x07);
			notification.put("open_type", 2);
			notification.put("pkg_content", pushMessageForm.getPkg_content());
			// notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);
			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(pushMessageForm.getChannelId()).addMsgExpires(pushMessageForm.getExpires() * 3600). // message有效时间
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

	public BaseDataWrapper queryStatisticDevice() throws PushClientException, PushServerException {
		BaseDataWrapper json = new BaseDataWrapper();
		PushKeyPair pair = new PushKeyPair(PushConfig.BAIDU_ANDROID_APIKEY, PushConfig.BAIDU_ANDROID_SECRETKEY);
		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);
		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			QueryStatisticDeviceRequest request = new QueryStatisticDeviceRequest().addDeviceType(3);
			// 5. http request
			QueryStatisticDeviceResponse response = pushClient.queryStatisticDevice(request);
			// Http请求结果解析打印
			if (null != response) {
				json.setSuccess(true);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("totalNum", response.getTotalNum());
				List<KeyValueForDevice> deviceStats = response.getResult();
				StringBuilder strBuilder = new StringBuilder();
				strBuilder.append("{\n");
				for (int i = 0; i < deviceStats.size(); i++) {
					KeyValueForDevice keyValue = deviceStats.get(i);
					if (i != 0) {
						strBuilder.append(",");
					}
					strBuilder.append("" + keyValue.getKey() + ":{" + "newDeviceNum="
							+ keyValue.getValue().getNewDevNum() + ",delDeviceNum=" + keyValue.getValue().getDelDevNum()
							+ ",onlineDeviceNum=" + keyValue.getValue().getOnlineDevNum() + ",addUpDeviceNum="
							+ keyValue.getValue().getAddUpDevNum() + ",totalDeviceNum="
							+ keyValue.getValue().getTotalDevNum() + "}");
				}
				strBuilder.append("}");
				data.put("result", strBuilder.toString());
				json.setData(data);
			}
		} catch (PushClientException e) {
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

	@Override
	public BaseDataWrapper pushMsgToAll(PushMessageForm pushMessageForm)
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
			PushMsgToAllRequest request = new PushMsgToAllRequest();
			if (pushMessageForm.getMsgType() == 1) {
				JSONObject notification = new JSONObject();
				notification.put("title", pushMessageForm.getTitle());
				notification.put("description", pushMessageForm.getContent());
				notification.put("notification_builder_id", 0);
				notification.put("notification_basic_style", 4);
				notification.put("open_type", 2);
				notification.put("pkg_content", pushMessageForm.getPkg_content());
				// notification.put("url", "http://push.baidu.com");
				JSONObject jsonCustormCont = new JSONObject();
				jsonCustormCont.put("key", "value"); // 自定义内容，key-value
				notification.put("custom_content", jsonCustormCont);
				if (pushMessageForm.getSendTime() == null) {
					request = new PushMsgToAllRequest().addMsgExpires(pushMessageForm.getExpires() * 3600)
							.addMessageType(pushMessageForm.getMsgType()).addMessage(pushMessageForm.getContent())
							.addDeviceType(pushMessageForm.getDeviceType()).addMessage(notification.toString());
				} else {
					request = new PushMsgToAllRequest().addMsgExpires(pushMessageForm.getExpires() * 3600)
							.addMessageType(pushMessageForm.getMsgType()).addMessage(pushMessageForm.getContent())
							.addSendTime(System.currentTimeMillis() / 1000 + 70)
							.addDeviceType(pushMessageForm.getDeviceType()).addMessage(notification.toString());
				}
			} else if (pushMessageForm.getMsgType() == 0) {
				if (pushMessageForm.getSendTime() == null) {
					request = new PushMsgToAllRequest().addMsgExpires(pushMessageForm.getExpires() * 3600)
							.addMessageType(pushMessageForm.getMsgType()).addMessage(pushMessageForm.getContent())
							.addDeviceType(pushMessageForm.getDeviceType());
				} else {
					request = new PushMsgToAllRequest().addMsgExpires(pushMessageForm.getExpires() * 3600)
							.addMessageType(pushMessageForm.getMsgType()).addMessage(pushMessageForm.getContent())
							.addSendTime(System.currentTimeMillis() / 1000 + 70)
							.addDeviceType(pushMessageForm.getDeviceType());
				}
			}

			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("msgId", response.getMsgId());
			responseMap.put("sendTime", response.getSendTime());
			responseMap.put("timerId", response.getTimerId());
			json.setSuccess(true);
			json.setData(responseMap);
		} catch (PushClientException e) {
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
