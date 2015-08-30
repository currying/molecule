package com.toparchy.pushAdmin.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toparchy.NettyPush.services.PushService;
import com.toparchy.pushAdmin.entity.PushMessagePacket;
import com.toparchy.pushAdmin.views.BaseDataWrapper;
import com.toparchy.pushAdmin.views.push.PushMessageForm;

@Service
public class PushServiceProxy {

	@Autowired
	private PushService pushService;

	private Logger log = LoggerFactory.getLogger(PushServiceProxy.class);

	/**
	 * 消息推送
	 * 
	 * @param pushMessageBean
	 * @return
	 */
	public BaseDataWrapper push(PushMessageForm pushMessageBean) {
		log.info("pushMessage: {}",pushMessageBean.toString());
		BaseDataWrapper json = new BaseDataWrapper();
		PushMessagePacket pushMessage = null;

		// 消息序列化
		try {
			pushMessage = pushMessageBean.parsePushMessage();
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			return json;

		}

		// 推送消息
		try {
			pushService.sendOnLineClient(pushMessageBean.getClient(),pushMessage);
		} catch (Exception e) {
			json.setSuccess(false);
			return json;
		}
		json.setSuccess(true);
		return json;
	}

}
