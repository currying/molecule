package com.toparchy.molecule.netty;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PushService extends ConnPoolService {

	private ClientMessageRepository clientMessageRepository;

	/**
	 * 推送 <br>
	 * FIXME 大用户？注意优化!! juc 包
	 * 
	 * @param client
	 * 
	 * @param msg
	 */
	public void sendOnLineClient(Client client, PushMessagePacket pm) {
		Long msgId = new Date().getTime();

		List<Client> clients = Collections.unmodifiableList(super
				.queryClients(client));
		NettyConnection conn = null;
		int size = clients.size();
		if (size > 0) {
			for (Client clientInfo : clients) {
				try {
					conn = pool.get(clientInfo.getDid());
					if (conn != null) {
						this.send(msgId, clientInfo, pm, conn);
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}

			}
		} else {
		}

	}

	private void send(long msgId, ClientPacket client, PushMessagePacket pm,
			NettyConnection conn) throws Exception {

		// 发送推送消息
		String _msg = (GenericPacket.server_push_text)
				+ FastJsonUtils.toJsonString(pm);
		conn.writeAndFlush(_msg);

		// 标记消息已发送
		SendedMessage cm = new SendedMessage();
		cm.setDid(client.getDid());
		cm.setAppKey(client.getAppKey());
		cm.setMsgId(msgId);
		clientMessageRepository.insertSendedMessage(cm);

	}

}
