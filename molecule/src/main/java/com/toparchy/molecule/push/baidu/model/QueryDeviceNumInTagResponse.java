package com.toparchy.molecule.push.baidu.model;

import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;

public class QueryDeviceNumInTagResponse extends PushResponse{

	@JSonPath(path="response_params\\device_num")
	private int deviceNum;
	
	// get
	public int getDeviceNum () {
		return deviceNum;
	}
}
