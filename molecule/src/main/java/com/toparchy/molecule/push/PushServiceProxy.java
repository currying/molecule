package com.toparchy.molecule.push;

import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;

public interface PushServiceProxy<T> {
	public T pushMsgToSingleDevic(PushMessageForm pushMessageForm) throws PushClientException, PushServerException;
}
