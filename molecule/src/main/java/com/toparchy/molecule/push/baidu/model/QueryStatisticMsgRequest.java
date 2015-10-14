package com.toparchy.molecule.push.baidu.model;

import net.sf.json.JSONArray;

import com.toparchy.molecule.push.baidu.constants.BaiduPushConstants;
import com.toparchy.molecule.push.baidu.core.annotation.HttpParamKeyName;
import com.toparchy.molecule.push.baidu.core.annotation.R;

public class QueryStatisticMsgRequest extends PushRequest{

	@HttpParamKeyName(name=BaiduPushConstants.QUERY_TYPE, param=R.OPTIONAL)
	private String queryType = null;
	
	// set
	public void setQueryType (int queryType) {
		if (checkValue(queryType)) {
			this.queryType = String.valueOf(queryType);
		}
	}
	public void setQueryType (int[] queryType) {
		JSONArray json = new JSONArray();
		for (int i = 0; i < queryType.length; i++) {
			if (checkValue(queryType[i])) {
				json.add(i, queryType[i]);
			} else {
				continue;
			}
		}
		this.queryType = json.toString();
	}
	// add
	public QueryStatisticMsgRequest addQueryType (int queryType) {
		if (checkValue(queryType)) {
			this.queryType = String.valueOf(queryType);
		}
		return this;
	}
	public QueryStatisticMsgRequest addQueryTypeArray (int[] queryType) {
		JSONArray json = new JSONArray();
		for (int i = 0; i < queryType.length; i++) {
			if (checkValue(queryType[i])) {
				json.add(i, queryType[i]);
			} else {
				continue;
			}
		}
		this.queryType = json.toString();
		return this;
	}
    public QueryStatisticMsgRequest addDeviceType (Integer deviceType) {
    	this.deviceType = deviceType;
    	return this;
    }
	public QueryStatisticMsgRequest addExpires(Long requestTimeOut) {
		this.expires = requestTimeOut;
		return this;
	}
	
	private boolean checkValue (int value) {
		if (value >= 0 && value <= 7) return true;
		else return false;
	}
}
