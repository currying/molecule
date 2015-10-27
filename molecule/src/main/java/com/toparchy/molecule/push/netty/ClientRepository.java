package com.toparchy.molecule.push.netty;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

	public static long client_db_live_time = 1000 * 40;

	private List<Client> clients = new ArrayList<Client>();

	public void insertClient(ClientPacket c) {
		Client client = new Client();
		client.setAppKey(c.getAppKey());
		client.setOs(c.getOs());
		client.setDid(c.getDid());
		client.setExpireTime(client_db_live_time);
		clients.add(client);
	}

	public Client getClient(String did) {
		for (Client c : clients) {
			if (c.getDid() == did)
				return c;
		}
		return null;
	}

	public List<Client> queryClients(String appKey) {
		List<Client> newClients = new ArrayList<Client>();
		for (Client c : clients) {
			if (c.getAppKey().equals(appKey))
				newClients.add(c);
		}
		return newClients;
	}

	// public boolean touchClient(ClientPacket c) {
	// }
	//
	// public boolean delExpireClient() {
	// }

}
