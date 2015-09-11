package com.toparchy.molecule.permission;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class HttpSecurityConfiguration {

	public void onInit(@Observes SecurityConfigurationEvent event) {
		SecurityConfigurationBuilder builder = event.getBuilder();

		builder
			.http()
				.forGroup("basic")
					.authenticateWith()
						.form()
							.authenticationUri("/login.jsf")
								.loginPage("/login.jsf")
								.errorPage("/error.jsf")
								.restoreOriginalRequest()
				.forPath("/logout").logout()
					.redirectTo("/index.html")
				.forPath("/view/*", "basic")
				.forPath("/run", "basic")
				.forPath("/frameset", "basic")
				.forPath("/webcontent", "basic")
				.forPath("/report", "basic")
				.forPath("/rest/business/*", "basic")
				.forPath("/system/*", "basic")
//					.authorizeWith()
//					.group("Administrators")
					.authorizeWith()
					.role("ADMINISTRATOR");
	}
}
