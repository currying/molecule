package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.Group;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@RequestScoped
public class GroupListProducer {

	@Inject
	private GroupRepository groupRepository;

	private List<Group> groups;
	@Produces
	@Named
	private List<Account> currentAccounts;

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

	public List<Account> getCurrentAccounts() {
		return currentAccounts;
	}

	public void setCurrentAccounts(List<Account> currentAccounts) {
		this.currentAccounts = currentAccounts;
	}

	public Group getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(Group currentGroup) {
		this.currentGroup = currentGroup;
	}

	public void onRowSelect(SelectEvent event) {
		currentAccounts = groupRepository.findGroupAccount((Group) event.getObject());
	}

	public void onRowUnselect(UnselectEvent event) {
	}
}
