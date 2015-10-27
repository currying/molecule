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

import com.toparchy.molecule.permission.data.GroupRepository;
import com.toparchy.molecule.permission.model.Member;

@Model
@ViewScoped
public class GroupController implements Serializable {

	private static final long serialVersionUID = -5322775634231349467L;

	@Inject
	private GroupRepository groupRepository;

	@Produces
	@Named
	private List<Member> currentMembers;

	@Produces
	@Named
	private Group currentGroup;

	public List<Member> getCurrentMembers() {
		return currentMembers;
	}

	public void setCurrentMembers(List<Member> currentMembers) {
		this.currentMembers = currentMembers;
	}

	public Group getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}

	public void onRowSelect(SelectEvent event) {
		currentMembers = groupRepository.findGroupMember((Group) event.getObject());
	}

	public void onRowUnselect(UnselectEvent event) {
	}
}
