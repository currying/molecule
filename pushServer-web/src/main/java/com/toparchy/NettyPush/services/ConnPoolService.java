package com.toparchy.NettyPush.services;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toparchy.NettyPush.entity.packet.ClientPacket;
import com.toparchy.NettyPush.entity.persitence.Client;
import com.toparchy.NettyPush.repository.ClientRepository;
import com.toparchy.NettyPush.server.conn.NettyConnection;
import com.toparchy.NettyPush.server.conn.NettyConnectionPool;
import com.toparchy.NettyPush.utils.AssertUtils;

@Service
public abstract class ConnPoolService {
	private volatile boolean isRefleshing = false;

	@Autowired
	private ClientRepository clientInfoRepo;

	@Autowired
	protected NettyConnectionPool<String, NettyConnection> pool;

	protected List<Client> queryClients(ClientPacket client) {
		return clientInfoRepo.queryClients(client.getAppKey());
	}

	protected void saveClientInfo(ClientPacket clientPacket) {
		AssertUtils.notNull(clientPacket);
		AssertUtils.notNull(clientPacket.getDid());
		Client client = this.getClient(clientPacket.getDid());
		if (client == null) {
			clientInfoRepo.insertClient(clientPacket);
		} else {
			clientInfoRepo.touchClient(client);
		}

	}

	protected void resetClientExpire(ClientPacket client) {
		AssertUtils.notNull(client);
		AssertUtils.notNull(client.getDid());
		clientInfoRepo.touchClient(client);
	}

	protected void delExpireClientsOfDb() {
		this.isRefleshing = true;
		Timer refleshClientInfo = new Timer("refleshClientInfo");
		refleshClientInfo.schedule(new TimerTask() {
			@Override
			public void run() {
				clientInfoRepo.delExpireClient();
			}
		}, 1024, 1 * DateUtils.MILLIS_IN_HOUR);
	}

	protected Client getClient(String did) {
		return clientInfoRepo.getClient(did);
	}

	protected boolean isRefleshClient() {
		return isRefleshing;
	}

}
