package com.toparchy.molecule.push;

import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;

public interface PushServiceProxy<T> {
	public T pushMsgToSingleDevic(PushMessageForm messageForm) throws PushClientException, PushServerException;

	public T queryStatisticDevice() throws PushClientException, PushServerException;

	public T pushMsgToAll(PushMessageForm messageForm) throws PushClientException, PushServerException;
}
