package com.toparchy.NettyPush.repository;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.toparchy.NettyPush.entity.packet.ClientPacket;
import com.toparchy.NettyPush.entity.persitence.Client;

public interface ClientRepository {

	@SuppressWarnings("deprecation")
	public static long client_db_live_time = DateUtils.MILLIS_IN_SECOND * 40;

	public boolean insertClient(ClientPacket c);

	public Client getClient(String did);

	public List<Client> queryClients(String appKey);

	public boolean touchClient(ClientPacket c);

	public boolean delExpireClient();

}
