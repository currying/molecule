package com.toparchy.molecule.push.baidu.model;

import java.util.LinkedList;
import java.util.List;

import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;

public class QueryStatisticTopicResponse extends PushResponse{

	@JSonPath(path="response_params\\total_num")
	private int totalNum;
	
	@JSonPath(path="response_params\\result")
	private List<KeyValueForTopic> result = new LinkedList<KeyValueForTopic>();
	
	public int getTotalNum () {
		return totalNum;
	}
	public List<KeyValueForTopic> getResult () {
		return result;
	}
}
