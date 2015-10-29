package com.toparchy.molecule.permission.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.toparchy.molecule.permission.data.MemberEntityRepository;
import com.toparchy.molecule.permission.model.entity.DeviceEntity;
import com.toparchy.molecule.permission.rest.PushData;

@Stateless
public class DeviceBindService {
	@Inject
	private EntityManager moleculeEm;
	@Inject
	private MemberEntityRepository memberEntityRepository;

	public void deviceBind(String accountId, PushData pushData) {
		DeviceEntity device = new DeviceEntity();
		device.setChannelId(pushData.getPushChannelId());
		device.setDeviceType(pushData.getDeviceType());
		device.setState("1");
		device.setMemberEntity(memberEntityRepository.findById(accountId));
		moleculeEm.persist(device);
	}
}
