package com.toparchy.platform.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.annotations.PicketLink;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.model.basic.Realm;

import com.toparchy.platform.security.model.ApplicationRealm;

@Model
@SessionScoped
public class RealmSelector implements Serializable {

	private static final long serialVersionUID = 7554702499329473466L;

	@Inject
	private PartitionManager partitionManager;

	private Realm realm;

	@Produces
	@PicketLink
	public Realm select() {
		return this.realm;
	}

	@Produces
	@Named("supportedRealms")
	public Enum[] supportedRealms() {
		return ApplicationRealm.values();
	}

	public ApplicationRealm getRealm() {
		if (this.realm == null) {
			return null;
		}

		return ApplicationRealm.valueOf(this.realm.getName());
	}

	public void setRealm(ApplicationRealm realm) {
		this.realm = this.partitionManager.getPartition(Realm.class,
				realm.name());
	}
}
