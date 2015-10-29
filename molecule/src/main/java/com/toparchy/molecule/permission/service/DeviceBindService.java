package com.toparchy.molecule.permission.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;

import com.toparchy.molecule.permission.data.MemberRepository;
import com.toparchy.molecule.permission.model.Member;
import com.toparchy.molecule.permission.model.entity.DeviceEntity;
import com.toparchy.molecule.permission.rest.PushData;

@Stateless
public class DeviceBindService {
	@Inject
	private IdentityManager identityManager;
	@Inject
	private MemberRepository memberRepository;

	public void deviceBind(String accountId, PushData pushData) {
		DeviceEntity device = new DeviceEntity();
		device.setChannelId(pushData.getPushChannelId());
		device.setDeviceType(pushData.getDeviceType());
		device.setState("1");
		Member member = memberRepository.findById(accountId);
		member.addDevice(device);
		identityManager.update(member);
	}
}
