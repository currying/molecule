package com.toparchy.molecule.permission.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.picketlink.idm.model.basic.Group;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.toparchy.molecule.permission.data.GroupRepository;
import com.toparchy.molecule.permission.model.Member;

@Model
@ViewScoped
public class GroupMemberTreeView implements Serializable {

	private static final long serialVersionUID = 1033941438571615025L;

	@Inject
	private GroupRepository groupRepository;
	private List<Group> groups;
	private TreeNode root;

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public TreeNode getRoot() {
		return root;
	}

	@PostConstruct
	public void init() {
		groups = groupRepository.findAllOrderedByName();
		root = new DefaultTreeNode("Root", null);
		for (Group group : groups) {
			if (group.getParentGroup() == null)
				createTree(group, root);
		}
	}

	private void createTree(Object object, TreeNode root) {
		if (object instanceof Group) {
			TreeNode group = new DefaultTreeNode(((Group) object).getName());
			root.getChildren().add(group);
			List<Member> members = groupRepository.findGroupMember((Group) object);
			for (Member member : members) {
				createTree(member, group);
			}
		}
		if (object instanceof Member) {
			TreeNode member = new DefaultTreeNode(((Member) object).getLoginName());
			root.getChildren().add(member);
		}
	}
}
