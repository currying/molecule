package com.toparchy.molecule.comet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;

//@WebListener
public class NettosphereChat implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		Config.Builder b = new Config.Builder();
		b.resource(Chat.class).port(8088).host("192.168.111.106").build();
		Nettosphere s = new Nettosphere.Builder().config(b.build()).build();
		s.start();
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}
}
