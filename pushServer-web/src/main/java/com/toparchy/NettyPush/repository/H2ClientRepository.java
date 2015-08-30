package com.toparchy.NettyPush.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toparchy.NettyPush.entity.packet.ClientPacket;
import com.toparchy.NettyPush.entity.persitence.Client;

@Repository
public class H2ClientRepository implements ClientRepository {

	@Autowired
	private JdbcTemplateWrapper jdbc;

	@Override
	public boolean insertClient(ClientPacket c) {
		// 设置过期时间
		long expireTime = new Date(new Date().getTime()
				+ ClientRepository.client_db_live_time).getTime();
		String sql = " insert into tb_online_client(appkey,did,expireTime,appVer,os) values(?,?,?,?,?)";
		return jdbc.saveORUpdate(sql, new Object[] { c.getAppKey(), c.getDid(),
				expireTime, c.getAppVer(), c.getOs() }) == 1;
	}

	@Override
	public List<Client> queryClients(String appKey) {
		String sql = " select   appkey,did,expireTime from tb_online_client where appkey = ?  and expireTime > ? ";
		return jdbc
				.queryForBeanList(sql, Client.class, new Object[] { appKey ,new Date().getTime()});
	}

	@Override
	public boolean delExpireClient() {
		String sql = " delete from tb_online_client where expireTime < ? ";
		return jdbc.saveORUpdate(sql, new Object[] { new Date().getTime() }) >= 1;
	}

	@Override
	public boolean touchClient(ClientPacket c) {
		String sql = " update  tb_online_client set expireTime = ? where did = ?";
		return jdbc.saveORUpdate(sql,
				new Object[] { new Date().getTime() +  ClientRepository.client_db_live_time , c.getDid() }) == 1;
	}

	@Override
	public Client getClient(String did) {
		String sql = " select  appkey,did,expireTime from tb_online_client where did = ?";
		return jdbc.queryForBean(sql, Client.class, new Object[] { did });
	}

}
