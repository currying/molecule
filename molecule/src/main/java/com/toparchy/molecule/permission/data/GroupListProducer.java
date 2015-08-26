package com.toparchy.molecule.permission.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.model.basic.Group;

@RequestScoped
public class GroupListProducer {

	@Inject
	private GroupRepository groupRepository;

	private List<Group> groups;

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
}
