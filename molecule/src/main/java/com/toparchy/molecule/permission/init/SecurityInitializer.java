package com.toparchy.molecule.permission.init;

import static org.picketlink.idm.model.basic.BasicModel.addToGroup;
import static org.picketlink.idm.model.basic.BasicModel.getGroup;
import static org.picketlink.idm.model.basic.BasicModel.grantRole;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Role;

import com.toparchy.molecule.permission.data.ApplicationResourceRepository;
import com.toparchy.molecule.permission.data.ApplicationRoleRepository;
import com.toparchy.molecule.permission.model.Member;
import com.toparchy.molecule.permission.model.entity.ApplicationResource;
import com.toparchy.molecule.permission.model.entity.ApplicationRole;

@Singleton
@Startup
public class SecurityInitializer {

	@Inject
	private PartitionManager partitionManager;
	@Inject
	private EntityManager moleculeEm;
	@Inject
	private ApplicationRoleRepository applicationRoleRepository;
	@Inject
	private ApplicationResourceRepository applicationResourceRepository;

	@PostConstruct
	public void createUsers() {
		if (check()) {
			createGroup("adminstrators", "ADMINISTRATOR", "超级管理员");
			createGroup("wzxt_group", "WZXT_MATERIALSTORAGE", "生产调度员角色");
			createGroup("scxt_group", "SCXT_WORKINGHOURSVOLUME", "物资仓储员");
			addUser("admin", "admin", "王", "宇轩", "currying", "currying@qq.com", "18652848028", "adminstrators");
			addUser("user1", "user1", "测试", "1", "user1", "user1@qq.com", "user1", "wzxt_group");
			addUser("user2", "user2", "测试", "2", "user2", "user2@qq.com", "user2", "scxt_group");
			createUser("user3", "user3", "测试", "3", "user3", "user3@qq.com", "user3", "PUSHMESSAGE", "消息推送者");

			createApplicationRole(new ApplicationRole("ADMINISTRATOR", "超级管理员"));
			createApplicationRole(new ApplicationRole("SCXT_WORKINGHOURSVOLUME", "生产调度员角色"));
			createApplicationRole(new ApplicationRole("WZXT_MATERIALSTORAGE", "物资仓储员"));
			createApplicationRole(new ApplicationRole("PUSHMESSAGE", "消息推送者"));

			createApplicationResource(new ApplicationResource("Administrator", "超级管理", "REST"));
			createApplicationResource(new ApplicationResource("P00000001", "通过料单编号检索所有物资", "REST"));
			createApplicationResource(new ApplicationResource("P00000002", "通过料单编号检索所有物资（无标记）", "REST"));
			createApplicationResource(new ApplicationResource("P00000003", "通过料单编号检索所有物资（标记）", "REST"));
			createApplicationResource(new ApplicationResource("P00000004", "添加新物资", "REST"));
			createApplicationResource(new ApplicationResource("P00000005", "修改物资", "REST"));
			createApplicationResource(new ApplicationResource("P00000006", "删除物资", "REST"));
			createApplicationResource(new ApplicationResource("P00000007", "通过ID获取工时物量反馈", "REST"));
			createApplicationResource(new ApplicationResource("P00000008", "获取工时物量信息", "REST"));
			createApplicationResource(new ApplicationResource("P00000009", "消息推送给指定设备", "BASE"));
			createApplicationResource(new ApplicationResource("P00000010", "查询设备状态", "BASE"));
			createApplicationResource(new ApplicationResource("P00000011", "绑定设备", "REST"));

			createRoleResource();
		}
	}

	private boolean check() {
		if (applicationRoleRepository.findAll().size() <= 0)
			return true;
		else
			return false;
	}

	private void createUser(String loginName, String password_, String firstName, String lastName, String nickName,
			String email, String phoneNumber, String roleName, String aliasName) {
		Member user = new Member(loginName, firstName, lastName, nickName, email, phoneNumber);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		identityManager.add(user);
		Password password = new Password(password_);
		identityManager.updateCredential(user, password);
		Role role = new Role(roleName);
		identityManager.add(role);
		RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();
		grantRole(relationshipManager, user, role);
	}

	private void createGroup(String groupName, String roleName, String aliasName) {
		Group group = new Group(groupName);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		identityManager.add(group);
		identityManager.update(group);
		Role role = new Role(roleName);
		identityManager.add(role);
		RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();
		grantRole(relationshipManager, group, role);
	}

	private void addUser(String loginName, String password_, String firstName, String lastName, String nickName,
			String email, String phoneNumber, String groupName) {
		Member user = new Member(loginName, firstName, lastName, nickName, email, phoneNumber);
		IdentityManager identityManager = this.partitionManager.createIdentityManager();
		identityManager.add(user);
		Password password = new Password(password_);
		identityManager.updateCredential(user, password);
		RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();
		addToGroup(relationshipManager, user, getGroup(identityManager, groupName));
	}

	private void createApplicationRole(ApplicationRole applicationRole) {
		moleculeEm.persist(applicationRole);
	}

	private void createApplicationResource(ApplicationResource applicationResource) {
		moleculeEm.persist(applicationResource);
	}

	private void createRoleResource() {
		ApplicationRole applicationRole = applicationRoleRepository.findByKey("ADMINISTRATOR");
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("Administrator"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000001"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000002"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000003"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000004"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000005"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000006"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000007"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000008"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000009"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000010"));
		applicationRole.addApplicationResource(applicationResourceRepository.findByKey("P00000011"));
		moleculeEm.persist(applicationRole);

		ApplicationRole applicationRole2 = applicationRoleRepository.findByKey("SCXT_WORKINGHOURSVOLUME");
		applicationRole2.addApplicationResource(applicationResourceRepository.findByKey("P00000007"));
		applicationRole2.addApplicationResource(applicationResourceRepository.findByKey("P00000008"));
		applicationRole2.addApplicationResource(applicationResourceRepository.findByKey("P00000011"));

		moleculeEm.persist(applicationRole2);

		ApplicationRole applicationRole3 = applicationRoleRepository.findByKey("WZXT_MATERIALSTORAGE");
		applicationRole3.addApplicationResource(applicationResourceRepository.findByKey("P00000001"));
		applicationRole3.addApplicationResource(applicationResourceRepository.findByKey("P00000002"));
		applicationRole3.addApplicationResource(applicationResourceRepository.findByKey("P00000003"));
		applicationRole3.addApplicationResource(applicationResourceRepository.findByKey("P00000004"));
		applicationRole3.addApplicationResource(applicationResourceRepository.findByKey("P00000005"));
		applicationRole3.addApplicationResource(applicationResourceRepository.findByKey("P00000006"));
		applicationRole3.addApplicationResource(applicationResourceRepository.findByKey("P00000011"));

		moleculeEm.persist(applicationRole3);

		ApplicationRole applicationRole4 = applicationRoleRepository.findByKey("PUSHMESSAGE");
		applicationRole4.addApplicationResource(applicationResourceRepository.findByKey("P00000009"));
		applicationRole4.addApplicationResource(applicationResourceRepository.findByKey("P00000010"));
		applicationRole4.addApplicationResource(applicationResourceRepository.findByKey("P00000011"));

		moleculeEm.persist(applicationRole4);
	}
}
