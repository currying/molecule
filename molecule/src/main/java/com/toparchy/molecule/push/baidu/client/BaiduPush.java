package com.toparchy.molecule.push.baidu.client;

import com.toparchy.molecule.push.baidu.exception.PushClientException;
import com.toparchy.molecule.push.baidu.exception.PushServerException;
import com.toparchy.molecule.push.baidu.model.AddDevicesToTagRequest;
import com.toparchy.molecule.push.baidu.model.AddDevicesToTagResponse;
import com.toparchy.molecule.push.baidu.model.CreateTagRequest;
import com.toparchy.molecule.push.baidu.model.CreateTagResponse;
import com.toparchy.molecule.push.baidu.model.DeleteDevicesFromTagRequest;
import com.toparchy.molecule.push.baidu.model.DeleteDevicesFromTagResponse;
import com.toparchy.molecule.push.baidu.model.DeleteTagRequest;
import com.toparchy.molecule.push.baidu.model.DeleteTagResponse;
import com.toparchy.molecule.push.baidu.model.PushBatchUniMsgRequest;
import com.toparchy.molecule.push.baidu.model.PushBatchUniMsgResponse;
import com.toparchy.molecule.push.baidu.model.PushMsgToAllRequest;
import com.toparchy.molecule.push.baidu.model.PushMsgToAllResponse;
import com.toparchy.molecule.push.baidu.model.PushMsgToSingleDeviceRequest;
import com.toparchy.molecule.push.baidu.model.PushMsgToSingleDeviceResponse;
import com.toparchy.molecule.push.baidu.model.PushMsgToSmartTagRequest;
import com.toparchy.molecule.push.baidu.model.PushMsgToSmartTagResponse;
import com.toparchy.molecule.push.baidu.model.PushMsgToTagRequest;
import com.toparchy.molecule.push.baidu.model.PushMsgToTagResponse;
import com.toparchy.molecule.push.baidu.model.QueryDeviceNumInTagRequest;
import com.toparchy.molecule.push.baidu.model.QueryDeviceNumInTagResponse;
import com.toparchy.molecule.push.baidu.model.QueryMsgStatusRequest;
import com.toparchy.molecule.push.baidu.model.QueryMsgStatusResponse;
import com.toparchy.molecule.push.baidu.model.QueryStatisticDeviceRequest;
import com.toparchy.molecule.push.baidu.model.QueryStatisticDeviceResponse;
import com.toparchy.molecule.push.baidu.model.QueryStatisticMsgRequest;
import com.toparchy.molecule.push.baidu.model.QueryStatisticMsgResponse;
import com.toparchy.molecule.push.baidu.model.QueryStatisticTopicRequest;
import com.toparchy.molecule.push.baidu.model.QueryStatisticTopicResponse;
import com.toparchy.molecule.push.baidu.model.QueryTagsRequest;
import com.toparchy.molecule.push.baidu.model.QueryTagsResponse;
import com.toparchy.molecule.push.baidu.model.QueryTimerListRequest;
import com.toparchy.molecule.push.baidu.model.QueryTimerListResponse;
import com.toparchy.molecule.push.baidu.model.QueryTimerRecordsRequest;
import com.toparchy.molecule.push.baidu.model.QueryTimerRecordsResponse;
import com.toparchy.molecule.push.baidu.model.QueryTopicListRequest;
import com.toparchy.molecule.push.baidu.model.QueryTopicListResponse;
import com.toparchy.molecule.push.baidu.model.QueryTopicRecordsRequest;
import com.toparchy.molecule.push.baidu.model.QueryTopicRecordsResponse;

public interface BaiduPush {

	public PushMsgToSingleDeviceResponse pushMsgToSingleDevice (
			PushMsgToSingleDeviceRequest request) throws PushClientException,
		    PushServerException;
	
	public PushMsgToAllResponse pushMsgToAll (
			PushMsgToAllRequest request) throws PushClientException,
		    PushServerException;
	
	public PushMsgToTagResponse pushMsgToTag (
			PushMsgToTagRequest request) throws PushClientException, 
	PushServerException;
	
	public PushMsgToSmartTagResponse pushMsgToSmartTag (
			PushMsgToSmartTagRequest request) throws PushClientException, 
	PushServerException;
	
	public PushBatchUniMsgResponse pushBatchUniMsg (
			PushBatchUniMsgRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryMsgStatusResponse queryMsgStatus (
			QueryMsgStatusRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTimerRecordsResponse queryTimerRecords (
			QueryTimerRecordsRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTopicRecordsResponse queryTopicRecords (
			QueryTopicRecordsRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTimerListResponse queryTimerList (
			QueryTimerListRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTopicListResponse queryTopicList (
			QueryTopicListRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTagsResponse queryTags (
			QueryTagsRequest request) throws PushClientException, 
	PushServerException;
	
	public CreateTagResponse createTag (
			CreateTagRequest request) throws PushClientException, 
	PushServerException;
	
	public DeleteTagResponse deleteTag (
			DeleteTagRequest request) throws PushClientException, 
	PushServerException;
	
	public AddDevicesToTagResponse addDevicesToTag (
			AddDevicesToTagRequest request) throws PushClientException, 
	PushServerException;
	
	public DeleteDevicesFromTagResponse deleteDevicesFromTag (
			DeleteDevicesFromTagRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryDeviceNumInTagResponse queryDeviceNumInTag (
			QueryDeviceNumInTagRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryStatisticMsgResponse queryStatisticMsg (
			QueryStatisticMsgRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryStatisticTopicResponse queryStatisticTopic (
			QueryStatisticTopicRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryStatisticDeviceResponse queryStatisticDevice (
			QueryStatisticDeviceRequest request) throws 
	PushClientException, PushServerException;
	
}
