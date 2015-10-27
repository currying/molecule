package com.toparchy.molecule.push.webSocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocket {

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {

		System.out.println("Received: " + message);

		session.getBasicRemote().sendText("This is the first server message");

		int sentMessages = 0;
		while (sentMessages < 3) {
			Thread.sleep(5000);
			session.getBasicRemote().sendText("This is an intermediate server message. Count: " + sentMessages);
			sentMessages++;
		}

		session.getBasicRemote().sendText("This is the last server message");
	}

	@OnOpen
	public void onOpen() {
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}
}