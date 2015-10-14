package com.toparchy.molecule.push.baidu.client;

import java.util.Map;

import com.toparchy.molecule.push.baidu.auth.PushKeyPair;
import com.toparchy.molecule.push.baidu.auth.signature.PushSignatureDigest;
import com.toparchy.molecule.push.baidu.constants.BaiduPushConstants;
import com.toparchy.molecule.push.baidu.core.callback.YunLogHttpCallBack;
import com.toparchy.molecule.push.baidu.core.exception.YunHttpClientException;
import com.toparchy.molecule.push.baidu.core.httpclient.YunHttpClient;
import com.toparchy.molecule.push.baidu.core.log.YunLogHandler;
import com.toparchy.molecule.push.baidu.core.model.HttpRestResponse;
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
import com.toparchy.molecule.push.baidu.model.PushRequest;
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
import com.toparchy.molecule.push.baidu.transform.PushRestRequestChecker;
import com.toparchy.molecule.push.baidu.transform.PushRestRequestMapper;
import com.toparchy.molecule.push.baidu.transform.PushRestResponseJsonUnmapper;

public class BaiduPushClient implements BaiduPush {

	private String apiKey = null;

	private String secretKey = null;

	private String host = null;

	private YunLogHttpCallBack logHttpCallback = new YunLogHttpCallBack();

	private PushRestResponseJsonUnmapper responseJsonUnmapper = new PushRestResponseJsonUnmapper();

	public BaiduPushClient(PushKeyPair pair) {
		this(pair, BaiduPushConstants.CHANNEL_REST_URL);
	}

	public BaiduPushClient(PushKeyPair pair, String host) {
		this.apiKey = pair.getApiKey();
		this.secretKey = pair.getSecretKey();
		this.host = host;
	}

    @Override
	public PushMsgToSingleDeviceResponse pushMsgToSingleDevice (
			PushMsgToSingleDeviceRequest request) 
	        throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH, 
				"single_device", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
				resp.getJsonResponse(), new PushMsgToSingleDeviceResponse());
	}

    @Override
	public PushMsgToAllResponse pushMsgToAll (
			PushMsgToAllRequest request) 
	        throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH, 
				"all", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
				resp.getJsonResponse(), new PushMsgToAllResponse());
	}
    
    @Override
    public PushMsgToTagResponse pushMsgToTag (PushMsgToTagRequest request) 
    		throws PushClientException, PushServerException {
    	HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH,
    			"tags", request);
    	return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
    			resp.getJsonResponse(), new PushMsgToTagResponse());
    }
    
    @Override
    public PushMsgToSmartTagResponse pushMsgToSmartTag (PushMsgToSmartTagRequest request) 
    		throws PushClientException, PushServerException {
    	HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH,
    			"tags", request);
    	return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
    			resp.getJsonResponse(), new PushMsgToSmartTagResponse());
    }
    
	@Override
	public PushBatchUniMsgResponse pushBatchUniMsg (PushBatchUniMsgRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH,
				"batch_device", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new PushBatchUniMsgResponse());
	}
	
	@Override
	public QueryMsgStatusResponse queryMsgStatus (QueryMsgStatusRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"query_msg_status", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryMsgStatusResponse());
	}
	
	@Override
	public QueryTimerRecordsResponse queryTimerRecords (QueryTimerRecordsRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"query_timer_records", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTimerRecordsResponse());
	}
	
	@Override
	public QueryTopicRecordsResponse queryTopicRecords (QueryTopicRecordsRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"query_topic_records", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTopicRecordsResponse());
	}
	
	@Override
	public QueryTimerListResponse queryTimerList (QueryTimerListRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTIMER,
				"query_list", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTimerListResponse());
	}
	
	@Override
	public QueryTopicListResponse queryTopicList (QueryTopicListRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTOPIC,
				"query_list", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTopicListResponse());
	}
	
	@Override
	public QueryTagsResponse queryTags (QueryTagsRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSAPP,
				"query_tags", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTagsResponse());
	}
	
	@Override
	public CreateTagResponse createTag (CreateTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSAPP,
				"create_tag", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new CreateTagResponse());
	}
	
	@Override
	public DeleteTagResponse deleteTag (DeleteTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSAPP,
				"del_tag", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new DeleteTagResponse());
	}
	
	@Override
	public AddDevicesToTagResponse addDevicesToTag (AddDevicesToTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTAG,
				"add_devices", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new AddDevicesToTagResponse());
	}
	
	@Override
	public DeleteDevicesFromTagResponse deleteDevicesFromTag (DeleteDevicesFromTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTAG,
				"del_devices", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new DeleteDevicesFromTagResponse());
	}
	
	@Override
	public QueryDeviceNumInTagResponse queryDeviceNumInTag (QueryDeviceNumInTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTAG,
				"device_num", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryDeviceNumInTagResponse());
	}
	

	@Override
	public QueryStatisticMsgResponse queryStatisticMsg (
			QueryStatisticMsgRequest request) throws PushClientException,
			PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"statistic_msg", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryStatisticMsgResponse());
	}

	@Override
	public QueryStatisticTopicResponse queryStatisticTopic (
			QueryStatisticTopicRequest request) throws PushClientException,
			PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"statistic_topic", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryStatisticTopicResponse());
	}

	@Override
	public QueryStatisticDeviceResponse queryStatisticDevice (
			QueryStatisticDeviceRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"statistic_device", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryStatisticDeviceResponse());
	}

	public void setChannelLogHandler(YunLogHandler logHandler) {
		logHttpCallback.setHandler(logHandler);
	}

	// -----------------------------------------------------------

	private HttpRestResponse process(String classType, String method, PushRequest request)
			throws PushClientException, PushServerException {

		PushRestRequestChecker checker = new PushRestRequestChecker();
		checker.validate(request);

		PushRestRequestMapper mapper = new PushRestRequestMapper();
		Map<String, String> params = mapper.marshall(request);
		params.put("apikey", apiKey);

		String surl = obtainIntegrityUrl(classType, method);

		PushSignatureDigest digest = new PushSignatureDigest();
		String sign = digest.digest(BaiduPushConstants.HTTPMETHOD, surl, secretKey, params);
		params.put("sign", sign);
		
		YunHttpClient client = new YunHttpClient();
		client.addHttpCallback(logHttpCallback);
		
		try {
			return client.doExecutePostRequestResponse(surl, params);
		} catch(YunHttpClientException e) {
			throw new PushClientException(e.getMessage());
		}
	}

	private String obtainIntegrityUrl(String classType, String method) {
		String resurl = host;
		if (host.startsWith("http://") || host.startsWith("https://")) {
		} else {
			resurl = "http://" + host;
		}
		if (resurl.endsWith("/")) {
			resurl = resurl + "rest/3.0/";
		} else {
			resurl = resurl + "/rest/3.0/";
		}
		resurl = resurl + classType + "/" + method;
		return resurl;
	}

}
