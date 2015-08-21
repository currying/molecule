package com.toparchy.serial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

public class Testmsgpack {

	@Test
	public void test() throws IOException {
		List<String> src = new ArrayList<String>();
		src.add("msgpack");
		src.add("kumofs");
		src.add("viver");

		MessagePack msgpack = new MessagePack();
		byte[] raw = msgpack.write(src);

		List<String> dst = msgpack
				.read(raw, Templates.tList(Templates.TString));
		System.out.println(dst.get(0));
		System.out.println(dst.get(1));
		System.out.println(dst.get(2));

	}
}