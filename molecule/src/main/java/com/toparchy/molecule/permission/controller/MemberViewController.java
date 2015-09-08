package com.toparchy.molecule.permission.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.basic.Group;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.toparchy.molecule.permission.data.MemberRepository;
import com.toparchy.molecule.permission.model.Member;

@Model
@ViewScoped
public class MemberViewController implements Serializable {

	private static final long serialVersionUID = -3942443699486324323L;

	@Inject
	private MemberRepository memberRepository;

	@Produces
	@Named
	private Member currentMember;

	@Produces
	@Named
	private List<Group> currentGroups;

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
