package com.toparchy.molecule.push.netty;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class NettyListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		new NettyServer(11111).run();
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
