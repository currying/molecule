package com.toparchy.molecule.push.baidu.model;

import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;

public class TopicStatUnit {

	@JSonPath(path="ack")
	private int ack;
	
	public int getAckNum () {
		return ack;
	}
}
