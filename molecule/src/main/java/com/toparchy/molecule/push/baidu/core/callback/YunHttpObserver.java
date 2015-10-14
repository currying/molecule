package com.toparchy.molecule.push.baidu.core.callback;

import com.toparchy.molecule.push.baidu.core.event.YunHttpEvent;

public interface YunHttpObserver {
	
	public void onHandle(YunHttpEvent event);
	
}
