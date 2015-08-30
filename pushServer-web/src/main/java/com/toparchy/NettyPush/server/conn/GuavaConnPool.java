package com.toparchy.NettyPush.server.conn;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.toparchy.NettyPush.repository.ClientRepository;

/**
 * 
 * 使用guava 存储tcp本地连接
 * 
 * @author flatychen
 * 
 */
public class GuavaConnPool implements
		NettyConnectionPool<String, NettyConnection> {

	private Logger log = LoggerFactory.getLogger(GuavaConnPool.class);

	private LoadingCache<String, NettyConnection> cache = CacheBuilder
			.newBuilder()
			.expireAfterAccess(ClientRepository.client_db_live_time,
					TimeUnit.MILLISECONDS)
			.removalListener(new RemovalListener<String, NettyConnection>() {
				@Override
				public void onRemoval(
						RemovalNotification<String, NettyConnection> notification) {
					log.debug(notification.getKey() + " is removed ");
				}
			}).build(new CacheLoader<String, NettyConnection>() {
				@Override
				public NettyConnection load(String key) throws Exception {
					NettyConnection conn = cache.get(key);
					if (conn != null) {
						log.info("---> refresh key {} ", key);
						return conn;
					} else {
						log.info("---> loading a not exist key {} ", key);
						return null;
					}
				}

			});

	@Override
	public boolean set(String key, NettyConnection t) {
		cache.put(key, t);
		return false;
	}

	@SuppressWarnings("finally")
	@Override
	public NettyConnection get(String key) {
		NettyConnection conn = null;
		try {
			conn = cache.get(key);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}

	@Override
	public boolean touch(String key) {
		cache.refresh(key);
		return true;
	}

	@Override
	public Map<String, NettyConnection> asMap() {
		return cache.asMap();
	}

}
