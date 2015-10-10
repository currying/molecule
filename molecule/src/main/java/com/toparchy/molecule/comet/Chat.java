package com.toparchy.molecule.comet;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ManagedService(path = "/chat")
public class Chat {
	private final Logger logger = LoggerFactory.getLogger(Chat.class);

	@Ready
	public void onReady(final AtmosphereResource r) {
		logger.info("Browser {} connected.", r.uuid());
	}

	@Disconnect
	public void onDisconnect(AtmosphereResourceEvent event) {
		if (event.isCancelled()) {
			logger.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
		} else if (event.isClosedByClient()) {
			logger.info("Browser {} closed the connection", event.getResource().uuid());
		}
	}

	@org.atmosphere.config.service.Message(encoders = { JacksonEncoder.class }, decoders = { JacksonDecoder.class })
	public Message onMessage(Message message) throws IOException {
		logger.info("{} just send {}", message.getAuthor(), message.getMessage());
		return message;
	}

}