//package com.toparchy.molecule.push.webSocket;
//
//import java.io.IOException;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import org.atmosphere.cpr.AtmosphereResource;
//import org.atmosphere.nettosphere.Config;
//import org.atmosphere.nettosphere.Handler;
//import org.atmosphere.nettosphere.Nettosphere;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@WebListener
//public class NettosphereChat implements ServletContextListener {
//
//	@Override
//	public void contextInitialized(ServletContextEvent sce) {
//		Nettosphere server = new Nettosphere.Builder()
//				.config(new Config.Builder().host("127.0.0.1").port(8088).resource(new Handler() {
//					public void handle(AtmosphereResource r) {
//						try {
//							r.getResponse().write("Hello World").write(" from Nettosphere").flushBuffer();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}).build()).build();
//		server.start();
//	}
//
//	@Override
//	public void contextDestroyed(ServletContextEvent sce) {
//
//	}
//
//}