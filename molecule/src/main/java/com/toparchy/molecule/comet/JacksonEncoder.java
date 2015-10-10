package com.toparchy.molecule.comet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Encoder;

import java.io.IOException;

public class JacksonEncoder implements Encoder<Message, String> {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public String encode(Message m) {
		try {
			return mapper.writeValueAsString(m);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
