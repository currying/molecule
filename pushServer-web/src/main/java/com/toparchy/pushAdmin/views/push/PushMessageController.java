package com.toparchy.pushAdmin.views.push;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toparchy.NettyPush.entity.persitence.Client;
import com.toparchy.pushAdmin.services.PushServiceProxy;
import com.toparchy.pushAdmin.views.BaseDataWrapper;

@Controller
@RequestMapping("/pushMessage")
public class PushMessageController {

	private static Logger log = LoggerFactory
			.getLogger(PushMessageController.class);

	@Autowired
	private PushServiceProxy pushServiceProxy;

	@RequestMapping("/new")
	public String news() {
		return "pushMessage/new";
	}

	@RequestMapping("/create")
	@ResponseBody
	public BaseDataWrapper create(@Valid PushMessageForm msg) {
		
		// 手动设定过滤信息
		Client clientsParams = new Client();
		clientsParams.setAppKey("apptest");
		
		msg.setClient(clientsParams);
		return pushServiceProxy.push(msg);
	}
}
