package com.toparchy.molecule.comet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Decoder;

import java.io.IOException;

public class JacksonDecoder implements Decoder<String, Message> {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public Message decode(String s) {
		try {
			return mapper.readValue(s, Message.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
