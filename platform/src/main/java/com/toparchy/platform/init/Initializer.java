package com.toparchy.platform.init;

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
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.IdentityType;
import org.picketlink.idm.model.basic.Group;
import org.picketlink.idm.model.basic.Realm;
import org.picketlink.idm.model.basic.Role;
//import org.picketlink.idm.model.basic.User;

import com.toparchy.platform.annotation.PlatformData;
import com.toparchy.platform.model.Person;
import com.toparchy.platform.security.model.ApplicationRealm;
import com.toparchy.platform.security.model.ApplicationRole;
import com.toparchy.platform.security.model.User;
import com.toparchy.platform.ui.model.Layout;
import com.toparchy.platform.ui.model.PositionLayout;

@Singleton
@Startup
public class Initializer {

	@Inject
	private PartitionManager partitionManager;
	@Inject
	@PlatformData
	private EntityManager platformEm;
	private Person personAdmin;
	private Person person;
	private Person person1;
	private Person person11;
	private Person person12;
	private Person person2;
	private Person person21;
	private Person person22;
	private Person person3;
	private Layout layout;
	private PositionLayout northLayout;
	private PositionLayout north_northLayout;
	private PositionLayout north_southLayout;
	private PositionLayout north_westLayout;
	private PositionLayout north_eastLayout;
	private PositionLayout north_centerLayout;
	private PositionLayout southLayout;
	private PositionLayout south_northLayout;
	private PositionLayout south_southLayout;
	private PositionLayout south_westLayout;
	private PositionLayout south_eastLayout;
	private PositionLayout south_centerLayout;
	private PositionLayout westLayout;
	private PositionLayout west_northLayout;
	private PositionLayout west_southLayout;
	private PositionLayout west_westLayout;
	private PositionLayout west_eastLayout;
	private PositionLayout west_centerLayout;
	private PositionLayout eastLayout;
	private PositionLayout east_northLayout;
	private PositionLayout east_southLayout;
	private PositionLayout east_westLayout;
	private PositionLayout east_eastLayout;
	private PositionLayout east_centerLayout;
	private PositionLayout centerLayout;
	private PositionLayout center_northLayout;
	private PositionLayout center_southLayout;
	private PositionLayout center_westLayout;
	private PositionLayout center_eastLayout;
	private PositionLayout center_centerLayout;

	@PostConstruct
	public void create() {
		for (ApplicationRealm realm : ApplicationRealm.values()) {
			createDefaultRealm(realm.name());
		}
		createDefaultLayout();
	}

	private void createDefaultRealm(String realmName) {
		Realm partition = partitionManager.getPartition(Realm.class, realmName);
		if (partition == null) {
			partition = new Realm(realmName);
			partitionManager.add(partition);
		}
		IdentityManager identityManager = this.partitionManager
				.createIdentityManager(partition);
		Group group = createGroup(identityManager, "分公司");
		Group group1 = createGroup(identityManager, "100分厂", "分公司");
		Group group11 = createGroup(identityManager, "1号车间", "分公司/100分厂");
		Group group12 = createGroup(identityManager, "2号车间", "分公司/100分厂");
		Group group2 = createGroup(identityManager, "101分厂", "分公司");
		Group group21 = createGroup(identityManager, "1号车间", "分公司/101分厂");
		Group group22 = createGroup(identityManager, "2号车间", "分公司/101分厂");
		Group group3 = createGroup(identityManager, "102分厂", "分公司");
		Group group4 = createGroup(identityManager, "信息中心", "分公司");

		Role role1 = createRole(identityManager,
				ApplicationRole.GROUPMANAGER.name());
		Role role2 = createRole(identityManager,
				ApplicationRole.GROUPNORMALUSER.name());
		Role role3 = createRole(identityManager,
				ApplicationRole.REALMMANAGER.name());
		Role role4 = createRole(identityManager,
				ApplicationRole.REALMNORMALUSER.name());
		Role role5 = createRole(identityManager,
				ApplicationRole.ROLEMANAGER.name());
		Role role6 = createRole(identityManager,
				ApplicationRole.ROLEMNORMALUSER.name());
		Role role7 = createRole(identityManager,
				ApplicationRole.USERMANAGER.name());
		Role role8 = createRole(identityManager,
				ApplicationRole.USERNORMALUSER.name());

		personAdmin = new Person("currying@gmail.com");
		personAdmin.setFirstName("王");
		personAdmin.setLastName("宇轩");
		User admin = createUser(identityManager, personAdmin, "admin", "admin");
		person = new Person("user@gmail.com");
		User user = createUser(identityManager, person, "user", "user");
		person1 = new Person("user1@gmail.com");
		User user1 = createUser(identityManager, person1, "user1", "user1");
		person11 = new Person("user11@gmail.com");
		User user11 = createUser(identityManager, person11, "user11", "user11");
		person12 = new Person("user12@gmail.com");
		User user12 = createUser(identityManager, person12, "user12", "user12");
		person2 = new Person("user2@gmail.com");
		User user2 = createUser(identityManager, person2, "user2", "user2");
		person21 = new Person("user21@gmail.com");
		User user21 = createUser(identityManager, person21, "user21", "user21");
		person22 = new Person("user22@gmail.com");
		User user22 = createUser(identityManager, person22, "user22", "user22");
		person3 = new Person("user3@gmail.com");
		User user3 = createUser(identityManager, person3, "user3", "user3");

		createRelationship(admin, group4);
		createRelationship(user, group);
		createRelationship(user1, group1);
		createRelationship(user11, group11);
		createRelationship(user12, group12);
		createRelationship(user2, group2);
		createRelationship(user21, group21);
		createRelationship(user22, group22);
		createRelationship(user3, group3);

		grant(group, role2);
		grant(group, role4);
		grant(group, role6);
		grant(group, role8);
		grant(group4, role1);
		grant(group4, role3);
		grant(group4, role5);
		grant(group4, role7);

	}

	private Role createRole(IdentityManager identityManager, String roleName) {
		Role role = new Role(roleName);
		identityManager.add(role);
		return role;
	}

	private void createRelationship(Account account, Group group) {
		RelationshipManager relationshipManager = this.partitionManager
				.createRelationshipManager();
		// grantGroupRole(relationshipManager, User, role, group);
		addToGroup(relationshipManager, account, group);
		// grantRole(relationshipManager, User, role);
	}

	private void grant(IdentityType identityType1, Role role) {
		RelationshipManager relationshipManager = this.partitionManager
				.createRelationshipManager();
		grantRole(relationshipManager, identityType1, role);
	}

	private User createUser(IdentityManager identityManager, Person person,
			String userName, String passWord) {
		User user = new User(userName);
		user.setPerson(person);
		identityManager.add(user);
		identityManager.updateCredential(user, new Password(passWord));
		return user;
	}

	private Group createGroup(IdentityManager identityManager, String groupName) {
		Group group = new Group(groupName);
		identityManager.add(group);
		return group;
	}

	private Group createGroup(IdentityManager identityManager,
			String subGroupName, String parentGroupName) {
		Group parentGroup = getGroup(identityManager, parentGroupName);
		Group subGroup = new Group(subGroupName, parentGroup);
		identityManager.add(subGroup);
		return subGroup;
	}

	private void createDefaultLayout() {
		layout = new Layout();
		layout.setSlidable(false);
		layout.setNorth("/shared/north.xhtml");
		layout.setSouth("/shared/south.xhtml");
		layout.setWest("/shared/west.xhtml");
		layout.setEast("/shared/east.xhtml");
		layout.setCenter("/shared/center.xhtml");
		northLayout = new PositionLayout();
		northLayout.setResizable(false);
		northLayout.setClosable(false);
		northLayout.setInitClosed(false);
		northLayout.setInitHidden(false);
		northLayout.setSize("60");
		northLayout.setSpacingOpen(0);
		northLayout.setPositionName("north");
		layout.addPositionLayout(northLayout);
		platformEm.persist(northLayout);

		north_northLayout = new PositionLayout();
		north_northLayout.setPositionName("north_north");
		north_northLayout.setHead("north_North");
		north_northLayout.setSpacingOpen(0);
		north_northLayout.setResizable(true);
		north_northLayout.setClosable(true);
		north_northLayout.setInitClosed(true);
		north_northLayout.setInitHidden(true);
		north_northLayout.setSrc("/shared/north_north.xhtml");
		layout.addPositionLayout(north_northLayout);
		platformEm.persist(north_northLayout);

		north_southLayout = new PositionLayout();
		north_southLayout.setPositionName("north_south");
		north_southLayout.setHead("north_South");
		north_southLayout.setSpacingOpen(0);
		north_southLayout.setResizable(true);
		north_southLayout.setClosable(true);
		north_southLayout.setInitClosed(true);
		north_southLayout.setInitHidden(true);
		north_southLayout.setSrc("/shared/north_south.xhtml");
		layout.addPositionLayout(north_southLayout);
		platformEm.persist(north_southLayout);

		north_westLayout = new PositionLayout();
		north_westLayout.setPositionName("north_west");
		north_westLayout.setHead("north_West");
		north_westLayout.setSpacingOpen(0);
		north_westLayout.setResizable(true);
		north_westLayout.setClosable(true);
		north_westLayout.setInitClosed(true);
		north_westLayout.setInitHidden(true);
		north_westLayout.setSrc("/shared/north_west.xhtml");
		layout.addPositionLayout(north_westLayout);
		platformEm.persist(north_westLayout);

		north_eastLayout = new PositionLayout();
		north_eastLayout.setPositionName("north_east");
		north_eastLayout.setHead("north_East");
		north_eastLayout.setSpacingOpen(0);
		north_eastLayout.setResizable(true);
		north_eastLayout.setClosable(true);
		north_eastLayout.setInitClosed(true);
		north_eastLayout.setInitHidden(true);
		north_eastLayout.setSrc("/shared/north_east.xhtml");
		layout.addPositionLayout(north_eastLayout);
		platformEm.persist(north_eastLayout);

		north_centerLayout = new PositionLayout();
		north_centerLayout.setPositionName("north_center");
		north_centerLayout.setHead("north_Center");
		north_centerLayout.setSpacingOpen(0);
		north_centerLayout.setResizable(false);
		north_centerLayout.setClosable(false);
		north_centerLayout.setSrc("/shared/north_center.xhtml");
		layout.addPositionLayout(north_centerLayout);
		platformEm.persist(north_centerLayout);

		southLayout = new PositionLayout();
		southLayout.setResizable(false);
		southLayout.setClosable(false);
		southLayout.setInitClosed(false);
		southLayout.setInitHidden(false);
		southLayout.setSize("28");
		southLayout.setSpacingOpen(0);
		southLayout.setPositionName("south");
		layout.addPositionLayout(southLayout);
		platformEm.persist(southLayout);

		south_northLayout = new PositionLayout();
		south_northLayout.setPositionName("south_north");
		south_northLayout.setHead("south_North");
		south_northLayout.setSpacingOpen(0);
		south_northLayout.setResizable(false);
		south_northLayout.setClosable(false);
		south_northLayout.setInitClosed(true);
		south_northLayout.setInitHidden(true);
		south_northLayout.setSrc("/shared/south_north.xhtml");
		layout.addPositionLayout(south_northLayout);
		platformEm.persist(south_northLayout);

		south_southLayout = new PositionLayout();
		south_southLayout.setPositionName("south_south");
		south_southLayout.setHead("south_South");
		south_southLayout.setSpacingOpen(0);
		south_southLayout.setResizable(false);
		south_southLayout.setClosable(false);
		south_southLayout.setInitClosed(true);
		south_southLayout.setInitHidden(true);
		south_southLayout.setSrc("/shared/south_south.xhtml");
		layout.addPositionLayout(south_southLayout);
		platformEm.persist(south_southLayout);

		south_westLayout = new PositionLayout();
		south_westLayout.setPositionName("south_west");
		south_westLayout.setHead("south_West");
		south_westLayout.setSpacingOpen(0);
		south_westLayout.setResizable(false);
		south_westLayout.setClosable(false);
		south_westLayout.setInitClosed(true);
		south_westLayout.setInitHidden(true);
		south_westLayout.setSrc("/shared/south_west.xhtml");
		layout.addPositionLayout(south_westLayout);
		platformEm.persist(south_westLayout);

		south_eastLayout = new PositionLayout();
		south_eastLayout.setPositionName("south_east");
		south_eastLayout.setHead("south_East");
		south_eastLayout.setSpacingOpen(0);
		south_eastLayout.setResizable(false);
		south_eastLayout.setClosable(false);
		south_eastLayout.setInitClosed(true);
		south_eastLayout.setInitHidden(true);
		south_eastLayout.setSrc("/shared/south_east.xhtml");
		layout.addPositionLayout(south_eastLayout);
		platformEm.persist(south_eastLayout);

		south_centerLayout = new PositionLayout();
		south_centerLayout.setPositionName("south_center");
		south_centerLayout.setHead("south_Center");
		south_centerLayout.setSpacingOpen(0);
		south_centerLayout.setResizable(false);
		south_centerLayout.setClosable(false);
		south_centerLayout.setSrc("/shared/south_center.xhtml");
		layout.addPositionLayout(south_centerLayout);
		platformEm.persist(south_centerLayout);

		westLayout = new PositionLayout();
		westLayout.setResizable(true);
		westLayout.setClosable(true);
		westLayout.setInitClosed(false);
		westLayout.setInitHidden(false);
		westLayout.setSize("25%");
		westLayout.setMinSize(150);
		westLayout.setMaxSize(500);
		westLayout.setSpacingOpen(5);
		westLayout.setSpacingClosed(10);
		westLayout.setPositionName("west");
		layout.addPositionLayout(westLayout);
		platformEm.persist(westLayout);

		west_northLayout = new PositionLayout();
		west_northLayout.setClosable(false);
		west_northLayout.setResizable(false);
		west_northLayout.setSize("22%");
		west_northLayout.setPositionName("west_north");
		west_northLayout.setHead("West_North");
		west_northLayout.setSpacingOpen(0);
		west_northLayout.setHeadSrc("/shared/head.xhtml");
		west_northLayout.setSrc("/shared/west_north.xhtml");
		layout.addPositionLayout(west_northLayout);
		platformEm.persist(west_northLayout);

		west_southLayout = new PositionLayout();
		west_southLayout.setClosable(false);
		west_southLayout.setResizable(false);
		west_southLayout.setSize("8%");
		west_southLayout.setPositionName("west_south");
		west_southLayout.setSpacingOpen(0);
		west_southLayout.setHeadSrc("/shared/head.xhtml");
		west_southLayout.setSrc("/shared/west_south.xhtml");
		layout.addPositionLayout(west_southLayout);
		platformEm.persist(west_southLayout);

		west_westLayout = new PositionLayout();
		west_westLayout.setClosable(true);
		west_westLayout.setResizable(true);
		west_westLayout.setInitClosed(true);
		west_westLayout.setInitHidden(true);
		west_westLayout.setPositionName("west_west");
		west_westLayout.setSpacingOpen(0);
		west_westLayout.setHeadSrc("/shared/head.xhtml");
		west_westLayout.setSrc("/shared/west_west.xhtml");
		layout.addPositionLayout(west_westLayout);
		platformEm.persist(west_westLayout);

		west_eastLayout = new PositionLayout();
		west_eastLayout.setClosable(true);
		west_eastLayout.setResizable(true);
		west_eastLayout.setInitClosed(true);
		west_eastLayout.setInitHidden(true);
		west_eastLayout.setPositionName("west_east");
		west_eastLayout.setSpacingOpen(0);
		west_eastLayout.setHeadSrc("/shared/head.xhtml");
		west_eastLayout.setSrc("/shared/west_east.xhtml");
		layout.addPositionLayout(west_eastLayout);
		platformEm.persist(west_eastLayout);

		west_centerLayout = new PositionLayout();
		west_centerLayout.setClosable(false);
		west_centerLayout.setResizable(false);
		west_centerLayout.setMinSize(200);
		west_centerLayout.setPositionName("west_center");
		west_centerLayout.setSpacingOpen(0);
		west_centerLayout.setHeadSrc("/shared/head.xhtml");
		west_centerLayout.setSrc("/shared/west_center.xhtml");
		layout.addPositionLayout(west_centerLayout);
		platformEm.persist(west_centerLayout);

		eastLayout = new PositionLayout();
		eastLayout.setResizable(true);
		eastLayout.setClosable(true);
		eastLayout.setInitClosed(true);
		eastLayout.setInitHidden(true);
		eastLayout.setSize("15%");
		eastLayout.setMinSize(150);
		eastLayout.setMaxSize(500);
		eastLayout.setPositionName("east");
		eastLayout.setSpacingOpen(0);
		// eastLayout.setCenter("/shared/east_center.xhtml");
		layout.addPositionLayout(eastLayout);
		platformEm.persist(eastLayout);

		east_northLayout = new PositionLayout();
		east_northLayout.setClosable(true);
		east_northLayout.setResizable(true);
		east_northLayout.setInitClosed(true);
		east_northLayout.setInitHidden(true);
		east_northLayout.setPositionName("east_north");
		east_northLayout.setSrc("/shared/east_north.xhtml");
		layout.addPositionLayout(east_northLayout);
		platformEm.persist(east_northLayout);

		east_southLayout = new PositionLayout();
		east_southLayout.setClosable(true);
		east_southLayout.setResizable(true);
		east_southLayout.setInitClosed(true);
		east_southLayout.setInitHidden(true);
		east_southLayout.setPositionName("east_south");
		east_southLayout.setSrc("/shared/east_south.xhtml");
		layout.addPositionLayout(east_southLayout);
		platformEm.persist(east_southLayout);

		east_westLayout = new PositionLayout();
		east_westLayout.setClosable(true);
		east_westLayout.setResizable(true);
		east_westLayout.setInitClosed(true);
		east_westLayout.setInitHidden(true);
		east_westLayout.setPositionName("east_west");
		east_westLayout.setSrc("/shared/east_west.xhtml");
		layout.addPositionLayout(east_westLayout);
		platformEm.persist(east_westLayout);

		east_eastLayout = new PositionLayout();
		east_eastLayout.setClosable(true);
		east_eastLayout.setResizable(true);
		east_eastLayout.setInitClosed(true);
		east_eastLayout.setInitHidden(true);
		east_eastLayout.setPositionName("east_east");
		east_eastLayout.setSrc("/shared/east_east.xhtml");
		layout.addPositionLayout(east_eastLayout);
		platformEm.persist(east_eastLayout);

		east_centerLayout = new PositionLayout();
		east_centerLayout.setMinSize(200);
		east_centerLayout.setPositionName("east_center");
		east_centerLayout.setSrc("/shared/east_center.xhtml");
		layout.addPositionLayout(east_centerLayout);
		platformEm.persist(east_centerLayout);

		centerLayout = new PositionLayout();
		centerLayout.setResizable(false);
		centerLayout.setClosable(false);
		centerLayout.setMinWidth(200);
		centerLayout.setMinHeight(60);
		centerLayout.setPositionName("center");
		centerLayout.setSpacingOpen(0);
		// centerLayout.setNorth("/shared/center_north.xhtml");
		// centerLayout.setCenter("/shared/center_center.xhtml");
		layout.addPositionLayout(centerLayout);
		platformEm.persist(centerLayout);

		center_northLayout = new PositionLayout();
		center_northLayout.setSize("50%");
		center_northLayout.setPositionName("center_north");
		center_northLayout.setHeadSrc("/shared/head.xhtml");
		center_northLayout.setHead("center_North");
		center_northLayout.setSrc("/shared/center_north.xhtml");
		layout.addPositionLayout(center_northLayout);
		platformEm.persist(center_northLayout);

		center_southLayout = new PositionLayout();
		center_southLayout.setClosable(true);
		center_southLayout.setResizable(true);
		center_southLayout.setInitClosed(true);
		center_southLayout.setInitHidden(true);
		center_southLayout.setPositionName("center_south");
		center_southLayout.setHead("center_south");
		center_southLayout.setSrc("/shared/center_south.xhtml");
		layout.addPositionLayout(center_southLayout);
		platformEm.persist(center_southLayout);

		center_westLayout = new PositionLayout();
		center_westLayout.setClosable(true);
		center_westLayout.setResizable(true);
		center_westLayout.setInitClosed(true);
		center_westLayout.setInitHidden(true);
		center_westLayout.setPositionName("center_west");
		center_westLayout.setHead("center_west");
		center_westLayout.setSrc("/shared/center_west.xhtml");
		layout.addPositionLayout(center_westLayout);
		platformEm.persist(center_westLayout);

		center_eastLayout = new PositionLayout();
		center_eastLayout.setClosable(true);
		center_eastLayout.setResizable(true);
		center_eastLayout.setInitClosed(true);
		center_eastLayout.setInitHidden(true);
		center_eastLayout.setPositionName("center_east");
		center_eastLayout.setHead("center_east");
		center_eastLayout.setSrc("/shared/center_east.xhtml");
		layout.addPositionLayout(center_eastLayout);
		platformEm.persist(center_eastLayout);

		center_centerLayout = new PositionLayout();
		center_centerLayout.setMinHeight(60);
		center_centerLayout.setPositionName("center_center");
		center_centerLayout.setHeadSrc("/shared/head.xhtml");
		center_centerLayout.setHead("center_center");
		center_centerLayout.setSrc("/shared/center_center.xhtml");
		layout.addPositionLayout(center_centerLayout);
		platformEm.persist(center_centerLayout);

		platformEm.persist(layout);
		personAdmin.setLayout(layout);
		person.setLayout(layout);
		person1.setLayout(layout);
		person11.setLayout(layout);
		person12.setLayout(layout);
		person2.setLayout(layout);
		person21.setLayout(layout);
		person22.setLayout(layout);
		person3.setLayout(layout);
		platformEm.persist(personAdmin);
		platformEm.persist(person);
		platformEm.persist(person1);
		platformEm.persist(person11);
		platformEm.persist(person2);
		platformEm.persist(person21);
		platformEm.persist(person22);
		platformEm.persist(person3);
	}
}
