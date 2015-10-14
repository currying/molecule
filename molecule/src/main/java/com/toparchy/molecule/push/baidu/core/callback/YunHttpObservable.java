package com.toparchy.molecule.push.baidu.core.callback;

import java.util.List;

import com.toparchy.molecule.push.baidu.core.event.YunHttpEvent;

public interface YunHttpObservable {
	
	public void addHttpCallback(YunHttpObserver callback);
	
	public void addBatchHttpCallBack(List<YunHttpObserver> callbacks);
	
	public void removeCallBack(YunHttpObserver callback);
	
	public void notifyAndCallback(YunHttpEvent event);

}
