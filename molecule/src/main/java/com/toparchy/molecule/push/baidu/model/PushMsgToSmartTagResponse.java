package com.toparchy.molecule.push.baidu.model;

import com.toparchy.molecule.push.baidu.constants.BaiduPushConstants;
import com.toparchy.molecule.push.baidu.core.annotation.HttpParamKeyName;
import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;
import com.toparchy.molecule.push.baidu.core.annotation.R;

public class PushMsgToSmartTagResponse extends PushResponse {
	
	@JSonPath(path="response_params\\msg_id")
    @HttpParamKeyName(name=BaiduPushConstants.MSG_ID, param=R.REQUIRE)
    protected String msgId = null;
    
    @JSonPath(path="response_params\\timer_id")
    @HttpParamKeyName(name=BaiduPushConstants.TIMER_ID, param=R.OPTIONAL)
    protected String timerId;
    
    @JSonPath(path="response_params\\send_time")
    @HttpParamKeyName(name=BaiduPushConstants.SEND_TIME, param=R.REQUIRE)
    protected long sendTime;
    
    public String getMsgId () {
    	return msgId;
    }
    public String getTimerId () {
    	return timerId;
    }
    public long getSendTime () {
    	return sendTime;
    }
}
