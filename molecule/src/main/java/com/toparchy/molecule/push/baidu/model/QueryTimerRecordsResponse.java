package com.toparchy.molecule.push.baidu.model;

import java.util.List;
import java.util.LinkedList;

import com.toparchy.molecule.push.baidu.constants.BaiduPushConstants;
import com.toparchy.molecule.push.baidu.core.annotation.HttpParamKeyName;
import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;
import com.toparchy.molecule.push.baidu.core.annotation.R;

public class QueryTimerRecordsResponse extends PushResponse{

	@JSonPath(path="response_params\\timer_id")
	@HttpParamKeyName(name=BaiduPushConstants.TIMER_ID, param=R.REQUIRE)
	private String timerId;
	
	@JSonPath(path="response_params\\result")
	@HttpParamKeyName(name=BaiduPushConstants.TIMER_RECORDS, param=R.REQUIRE)
	private List<Record> timerRecords = new LinkedList<Record>();
	
	// get
	public String getTimerId () {
		return timerId;
	}
	public List<Record> getTimerRecords () {
		return timerRecords;
	}
}
