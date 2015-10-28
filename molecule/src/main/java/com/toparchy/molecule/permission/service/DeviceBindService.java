package com.toparchy.molecule.permission.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;

import com.toparchy.molecule.permission.data.MemberRepository;
import com.toparchy.molecule.permission.model.Member;

@Stateless
public class DeviceBindService {
	@Inject
	private IdentityManager identityManager;
	@Inject
	private MemberRepository memberRepository;

	public void deviceBind(String accountId, String channelId) {
		Member member = memberRepository.findById(accountId);
		member.setChannelId(channelId);
		identityManager.update(member);
	}
}
