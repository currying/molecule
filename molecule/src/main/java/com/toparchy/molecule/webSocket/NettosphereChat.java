package com.toparchy.molecule.webSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class NettosphereChat implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(Nettosphere.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Config.Builder b = new Config.Builder();
		b.resource(Chat.class)
				// For *-distrubution
				.resource("./webapps")
				// For mvn exec:java
				.resource("./src/main/resources")
				// For running inside an IDE
				.resource("./nettosphere-samples/chat/src/main/resources").port(8080).host("0.0.0.0").build();
		Nettosphere s = new Nettosphere.Builder().config(b.build()).build();
		s.start();

		logger.info("NettoSphere Chat Server started on port {}", 8080);
		logger.info("Type quit to stop the server");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}