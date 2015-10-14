package com.toparchy.molecule.push.baidu.model;

import com.toparchy.molecule.push.baidu.constants.BaiduPushConstants;
import com.toparchy.molecule.push.baidu.core.annotation.HttpParamKeyName;
import com.toparchy.molecule.push.baidu.core.annotation.R;
import com.toparchy.molecule.push.baidu.core.annotation.RangeRestrict;

public class QueryStatisticTopicRequest extends PushRequest{

	@HttpParamKeyName(name=BaiduPushConstants.TOPIC_ID, param=R.REQUIRE)
	@RangeRestrict(minLength=1, maxLength=128)
	private String topicId = null;
	
	// get
	public String getTopicId () {
		return topicId;
	}
	// set
	public void setTopicId (String topicId) {
		this.topicId = topicId;
	}
	// add
	public QueryStatisticTopicRequest addTopicId (String topicId) {
		this.topicId = topicId;
		return this;
	}
    public QueryStatisticTopicRequest addDeviceType (Integer deviceType) {
    	this.deviceType = deviceType;
    	return this;
    }
	public QueryStatisticTopicRequest addExpires(Long requestTimeOut) {
		this.expires = requestTimeOut;
		return this;
	}
}
