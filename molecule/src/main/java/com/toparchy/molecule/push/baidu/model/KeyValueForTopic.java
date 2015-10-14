package com.toparchy.molecule.push.baidu.model;

import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;

public class KeyValueForTopic {

	@JSonPath(path="key")
	private String timestamp;
	
	@JSonPath(path="value")
	private TopicStatUnit value = null;
	
	public void setKey (String key) {
		this.timestamp = key;
	}
	
	public void setValue (TopicStatUnit value) {
		this.value = value;
	}
	// get
	public String getKey () {
		return timestamp;
	}
	public TopicStatUnit getValue () {
		return value;
	}
}
