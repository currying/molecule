package com.toparchy.molecule.permission.model.basic;

import org.picketlink.idm.model.AbstractPartition;
import org.picketlink.idm.model.IdentityType;
import org.picketlink.idm.model.annotation.IdentityPartition;

@IdentityPartition(supportedTypes = { IdentityType.class })
public class Realm extends AbstractPartition {

	private static final long serialVersionUID = -2667438382506066497L;

	public static final String DEFAULT_REALM = "default";

	public Realm() {
		super(null);
	}

	public Realm(String name) {
		super(name);
	}

}
