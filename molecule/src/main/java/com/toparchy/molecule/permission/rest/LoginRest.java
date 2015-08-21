package com.toparchy.molecule.permission.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.picketlink.Identity;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.Grant;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.query.RelationshipQuery;

import com.toparchy.molecule.permission.model.Member;

@Path("/")
public class LoginRest {

	@Inject
	private Identity identity;
	@Inject
	private RelationshipManager relationshipManager;
	@Inject
	private DefaultLoginCredentials credentials;

	@POST
	@Path("/logout")
	public Response logout() {
		if (this.identity.isLoggedIn()) {
			Account account = this.identity.getAccount();
			identity.logout();
			return Response.ok()
					.entity("{\"loginName\" : \"" + ((Member) account).getLoginName() + "\",\"state\": \"logout\"}")
					.type(MediaType.APPLICATION_JSON_TYPE).build();
		}
		return Response.ok().entity("").type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@POST
	@Path("/login")
	public Response login(DefaultLoginCredentials credential) {
		if (!this.identity.isLoggedIn()) {
			this.credentials.setUserId(credential.getUserId());
			this.credentials.setPassword(credential.getPassword());
			this.identity.login();
		}
		if (this.identity.isLoggedIn()) {
			Account account = this.identity.getAccount();
			List<Role> roles = getUserRoles(account);
			AuthenticationResponse authenticationResponse = new AuthenticationResponse(account, roles);
			return Response.ok().entity(authenticationResponse).type(MediaType.APPLICATION_JSON_TYPE).build();
		}
		return Response.ok().entity("{\"loginName\" : \"" + credential.getUserId() + "\",\"state\" : \"fail\"}")
				.type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	private List<Role> getUserRoles(Account account) {
		RelationshipQuery<Grant> query = this.relationshipManager.createRelationshipQuery(Grant.class);

		query.setParameter(Grant.ASSIGNEE, account);

		List<Role> roles = new ArrayList<Role>();

		for (Grant grant : query.getResultList()) {
			roles.add(grant.getRole());
		}

		return roles;
	}

	private class AuthenticationResponse implements Serializable {

		private static final long serialVersionUID = -5999025817799965794L;
		private Account account;
		private List<Role> roles;

		public AuthenticationResponse(Account account, List<Role> roles) {
			this.account = account;
			this.roles = roles;
		}

		public Account getAccount() {
			return this.account;
		}

		public List<Role> getRoles() {
			return this.roles;
		}
	}
}
