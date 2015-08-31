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
public class GroupListProducer implements Serializable {

	private static final long serialVersionUID = -7889112874104863384L;

	@Inject
	private GroupRepository groupRepository;

	private List<Group> groups;
	@Produces
	@Named
	private List<Member> currentMembers;

	@Produces
	@Named
	private Group currentGroup;

	@Produces
	@Named
	public List<Group> getGroups() {
		return groups;
	}

	public void onGroupRepositoryListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Group group) {
		retrieveAllGroupRepositorysOrderedByName();
	}

	@PostConstruct
	public void retrieveAllGroupRepositorysOrderedByName() {
		groups = groupRepository.findAllOrderedByName();
	}

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
