package com.toparchy.molecule.push.netty;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class ConnPoolService {
	private volatile boolean isRefleshing = false;
	private static ClientRepository clientRepository = new ClientRepository();
	protected static NettyConnectionPool<String, NettyConnection> pool = new GuavaConnPool();

	protected List<Client> queryClients(ClientPacket client) {
		return clientRepository.queryClients(client.getAppKey());
	}

	protected void saveClientInfo(ClientPacket clientPacket) {
		AssertUtils.notNull(clientPacket);
		AssertUtils.notNull(clientPacket.getDid());
		Client client = this.getClient(clientPacket.getDid());
		if (client == null) {
			clientRepository.insertClient(clientPacket);
		} else {
			// clientInfoRepo.touchClient(client);
		}

	}

	protected void resetClientExpire(ClientPacket client) {
		AssertUtils.notNull(client);
		AssertUtils.notNull(client.getDid());
		// clientInfoRepo.touchClient(client);
	}

	protected void delExpireClientsOfDb() {
		this.isRefleshing = true;
		Timer refleshClientInfo = new Timer("refleshClientInfo");
		refleshClientInfo.schedule(new TimerTask() {
			@Override
			public void run() {
				// clientInfoRepo.delExpireClient();
			}
		}, 1024, 1 * 60 * 60 * 1000);
	}

	protected Client getClient(String did) {
		return clientRepository.getClient(did);
	}

	protected boolean isRefleshClient() {
		return isRefleshing;
	}

}
