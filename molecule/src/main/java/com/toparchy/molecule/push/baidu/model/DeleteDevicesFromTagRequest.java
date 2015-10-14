package com.toparchy.molecule.push.baidu.model;

import net.sf.json.JSONArray;

import com.toparchy.molecule.push.baidu.constants.BaiduPushConstants;
import com.toparchy.molecule.push.baidu.core.annotation.HttpParamKeyName;
import com.toparchy.molecule.push.baidu.core.annotation.R;
import com.toparchy.molecule.push.baidu.core.annotation.RangeRestrict;

public class DeleteDevicesFromTagRequest extends PushRequest{

	@HttpParamKeyName(name=BaiduPushConstants.TAG_NAME, param=R.REQUIRE)
	@RangeRestrict(minLength=1, maxLength=128)
	private String tagName = null;
	
    @HttpParamKeyName(name=BaiduPushConstants.CHANNEL_IDS, param=R.REQUIRE)
    private String channelIds = null;
    
    // get
    public String getTagName () {
    	return tagName;
    }
    public String getChannelIdsString () {
    	return channelIds;
    }
    public String[] getChannelIdsArray () {
    	JSONArray jsonChannelIds = JSONArray.fromObject(channelIds);
    	return (String[]) jsonChannelIds.toArray();
    }
    // set
    public void setTagName (String tagName) {
    	this.tagName = tagName;
    }
    public void setChannelIds (String[] channelIds) {
    	JSONArray tmpChannelIds = new JSONArray();
    	for (int i = 0; i < channelIds.length; i++){
    		tmpChannelIds.add(i, channelIds[i]);
    	}
    	this.channelIds = tmpChannelIds.toString();
    }
    // add
    public DeleteDevicesFromTagRequest addTagName (String tagName) {
    	this.tagName = tagName;
    	return this;
    }
    public DeleteDevicesFromTagRequest addChannelIds (String[] channelIds) {
    	JSONArray tmpChannelIds = new JSONArray();
    	for (int i = 0; i < channelIds.length; i++){
    		tmpChannelIds.add(i, channelIds[i]);
    	}
    	this.channelIds = tmpChannelIds.toString();
    	return this;
    }
    public DeleteDevicesFromTagRequest addDeviceType (Integer deviceType) {
    	this.deviceType = deviceType;
    	return this;
    }
	public DeleteDevicesFromTagRequest addExpires(Long requestTimeOut) {
    	this.expires = requestTimeOut;
		return this;
	}
}

