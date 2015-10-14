package com.toparchy.molecule.push.baidu.model;

import com.toparchy.molecule.push.baidu.core.annotation.JSonPath;

public class DeleteTagResponse extends PushResponse{

	@JSonPath(path="response_params\\tag")
	private String tagName = null;
	
	@JSonPath(path="response_params\\result")
	private int result;     // 0 -- successfully delete tag, 
	                        // 1 -- fail to delete tag.
	
	// get
	public String getTagName () {
		return tagName;
	}
	public int getResult () {
		return result;
	}
}
