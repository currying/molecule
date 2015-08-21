package com.toparchy.molecule.permission.rest;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.picketlink.Identity;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.Grant;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.query.RelationshipQuery;

@WebServlet(name = "loginServlet", urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -5865517696971703332L;

	@Inject
	private Identity identity;
	@Inject
	private RelationshipManager relationshipManager;
	@Inject
	private DefaultLoginCredentials credentials;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (!this.identity.isLoggedIn()) {
			this.credentials.setUserId(request.getParameter("username"));
			this.credentials.setPassword(request.getParameter("password"));
			this.identity.login();
		}
		if (this.identity.isLoggedIn()) {
			Account account = this.identity.getAccount();
			List<Role> roles = getUserRoles(account);
			AuthenticationResponse authenticationResponse = new AuthenticationResponse(
					account, roles);
			response.getWriter().write(authenticationResponse.account.getId());
		}
		if (!this.identity.isLoggedIn()) {
			response.getWriter().write(
					"{\"account\": {\"loginName\": \"fail\" }}");
		}
	}

	private List<Role> getUserRoles(Account account) {
		RelationshipQuery<Grant> query = this.relationshipManager
				.createRelationshipQuery(Grant.class);

		query.setParameter(Grant.ASSIGNEE, account);

		List<Role> roles = new ArrayList<Role>();

		for (Grant grant : query.getResultList()) {
			roles.add(grant.getRole());
		}

		return roles;
	}

	private class AuthenticationResponse implements Serializable {

		private static final long serialVersionUID = -5976090964672477965L;
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
