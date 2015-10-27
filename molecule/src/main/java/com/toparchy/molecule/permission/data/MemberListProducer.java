package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.toparchy.molecule.permission.model.entity.Member;

@ApplicationScoped
public class MemberListProducer {

	@Inject
	private MemberRepository memberRepository;

	@Produces
	@Named
	private List<Member> members;

	public List<Member> getMembers() {
		return members;
	}

	public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Member member) {
		retrieveAllMembersOrderedByName();
	}

	@PostConstruct
	public void retrieveAllMembersOrderedByName() {
		members = memberRepository.findAllOrderedByName();
	}
}
