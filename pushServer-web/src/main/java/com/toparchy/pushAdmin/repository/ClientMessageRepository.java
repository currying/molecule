package com.toparchy.pushAdmin.repository;

import java.util.List;

import com.toparchy.NettyPush.entity.packet.ClientPacket;
import com.toparchy.pushAdmin.entity.Message;
import com.toparchy.pushAdmin.entity.SendedMessage;


public interface ClientMessageRepository {

	 public boolean insertMessage(Message msg);
	
	 public boolean insertSendedMessage(SendedMessage sm);
	
	 public List<Message> queryClientMessage(ClientPacket clientInfo);

}
