package com.toparchy.molecule.push.baidu.model;

import java.util.List;

import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;

import java.util.LinkedList;

public class AddDevicesToTagResponse extends PushResponse{

	@JSonPath(path="response_params\\result")
	private List<DeviceInfo> devicesInfoAfterAdded = new LinkedList<DeviceInfo> ();
	
	// get
	public List<DeviceInfo> getDevicesInfoAfterAdded () {
		return devicesInfoAfterAdded;
	}
}
