package com.toparchy.molecule.netty;

import java.util.List;

public interface ClientMessageRepository {

	public boolean insertMessage(Message msg);

	public boolean insertSendedMessage(SendedMessage sm);

	public List<Message> queryClientMessage(ClientPacket clientInfo);

}
