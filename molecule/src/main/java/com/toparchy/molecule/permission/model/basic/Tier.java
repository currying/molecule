package com.toparchy.molecule.permission.model.basic;

import org.picketlink.idm.model.AbstractPartition;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.IdentityType;
import org.picketlink.idm.model.annotation.IdentityPartition;

@IdentityPartition(supportedTypes = { IdentityType.class }, unsupportedTypes = { Account.class })
public class Tier extends AbstractPartition {

	private static final long serialVersionUID = 7797059334915537276L;

	public Tier() {
		super(null);
	}

	public Tier(String name) {
		super(name);
	}

}
