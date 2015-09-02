package com.toparchy.molecule.permission.data;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.basic.Group;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.toparchy.molecule.permission.model.Member;

@Model
@ViewScoped
public class MemberListProducer implements Serializable {

	private static final long serialVersionUID = -7152262824975050189L;

	@Inject
	private MemberRepository memberRepository;

	private List<Member> members;

	@Produces
	@Named
	private Member currentMember;

	@Produces
	@Named
	private List<Group> currentGroups;

	@Produces
	@Named
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

	public Member getCurrentMember() {
		return currentMember;
	}

	public void setCurrentMember(Member currentMember) {
		this.currentMember = currentMember;
	}

	public List<Group> getCurrentGroups() {
		return currentGroups;
	}

	public void setCurrentGroups(List<Group> currentGroups) {
		this.currentGroups = currentGroups;
	}

	public void onRowSelect(SelectEvent event) {
		currentGroups = memberRepository.findMemberGroup((Member) event.getObject());
	}

	public void onRowUnselect(UnselectEvent event) {
	}
}
