package com.jzsoft.platform2.controller.member;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.MemberRepository;
import com.jzsoft.platform2.model.Member;

@Model
public class MemberListProducer implements Serializable {

	private static final long serialVersionUID = 8303786806093974151L;

	@Inject
	private MemberRepository memberRepository;

	private List<Member> members;

	@Produces
	@Named
	public List<Member> getMembers() {
		return members;
	}

	public void onMemberListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Member member) {
		retrieveAllMembersOrderedByName();
	}

	@PostConstruct
	public void retrieveAllMembersOrderedByName() {
		members = memberRepository.findAllOrderedByName();
	}

}
