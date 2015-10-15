package com.toparchy.molecule.netty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NettyPushService {
	@Inject
	private PushService pushService;

	/**
	 * 消息推送
	 * 
	 * @param pushMessageBean
	 * @return
	 */
	public BaseDataWrapper push(PushMessageForm pushMessageBean) {
		BaseDataWrapper json = new BaseDataWrapper();
		PushMessagePacket pushMessage = null;

		// 消息序列化
		try {
			pushMessage = pushMessageBean.parsePushMessage();
		} catch (Exception e) {
			json.setSuccess(false);
			return json;

		}

		// 推送消息
		try {
			pushService.sendOnLineClient(pushMessageBean.getClient(), pushMessage);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			return json;
		}
		json.setSuccess(true);
		return json;
	}

}
