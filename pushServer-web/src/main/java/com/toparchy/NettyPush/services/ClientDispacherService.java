package com.toparchy.NettyPush.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toparchy.NettyPush.entity.packet.ClientPacket;
import com.toparchy.NettyPush.entity.packet.GenericPacket;
import com.toparchy.NettyPush.server.conn.NettyConnection;
import com.toparchy.NettyPush.utils.FastJsonUtils;

/**
 * 服务端报文分发
 * 
 * @author flatychen
 * 
 */
@Service
public class ClientDispacherService extends ConnPoolService {

	@Autowired
	private PushService pushService;

	private static Logger log = LoggerFactory
			.getLogger(ClientDispacherService.class);

	/**
	 * 客户端报文分发
	 * 
	 * @param conn
	 * @param msg
	 */
	public void dispacher(NettyConnection conn, String data) {
		GenericPacket m = new GenericPacket(data);
		// 得到包命令
		int commond = m.getCommond();
		// 新连接
		if (commond == GenericPacket.client_connected) {
			this.validateAndSave(conn, m.getMessage());
			// 心跳,刷新客户端
		} else if (commond == GenericPacket.client_heart) {
			this.keepAliveOfClient(m.getMessage());
		} else {
			log.error(" invalid client packet type.");
		}

	}

	/**
	 * 检测新连接合法性，并保存
	 * 
	 * @param conn
	 * @param message
	 */
	private void validateAndSave(NettyConnection conn, String message) {
		log.info("new client:{}", message);
		ClientPacket client = null;

		try {
			client = FastJsonUtils.praseToObject(message, ClientPacket.class);
		} catch (Exception e) {
			log.error("连接发送包不合法: {}", e.getMessage());
			return;
		}

		// 检查是否有消息需要发送
		pushService.sendForNewClient(client, conn);

		// 保存连接信息于本地连接池中和DB中
		super.saveClientInfo(client);
		pool.set(client.getDid(), conn);

		// 自动刷新DB中客户端数据
		 this.startRefleshClientOfDb();

	}

	private void startRefleshClientOfDb() {
		if (!super.isRefleshClient()) {
			super.delExpireClientsOfDb();
		}
	}

	/**
	 * 
	 * 刷新客户端
	 * 
	 * @param conn
	 * @param message
	 */
	private void keepAliveOfClient(String message) {

		log.info("heartBeat:{}", message);
		
		ClientPacket client = FastJsonUtils.praseToObject(message,
				ClientPacket.class);

		pool.touch(client.getDid());
		resetClientExpire(client);
	}

}
