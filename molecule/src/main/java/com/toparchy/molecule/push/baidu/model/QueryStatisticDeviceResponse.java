package com.toparchy.molecule.push.baidu.model;

import java.util.LinkedList;
import java.util.List;

import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;

public class QueryStatisticDeviceResponse extends PushResponse{

	@JSonPath(path="response_params\\total_num")
	private int totalNum;
	
	@JSonPath(path="response_params\\result")
	private List<KeyValueForDevice> result = new LinkedList<KeyValueForDevice>();
	
	public int getTotalNum () {
		return totalNum;
	}
	public List<KeyValueForDevice> getResult () {
		return result;
	}
}
