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
							.authenticationUri("/login.xhtml")
								.loginPage("/login.xhtml")
								.errorPage("/error.xhtml")
								.restoreOriginalRequest()
				.forPath("/logout").logout()
					.redirectTo("/login.xhtml")
				.forPath("/view/*", "basic")
				.forPath("/run", "basic")
				.forPath("/frameset", "basic")
				.forPath("/webcontent", "basic")
				.forPath("/report", "basic")
				.forPath("/rest/business/*", "basic")
				.forPath("/system/*", "basic")
					.authorizeWith()
					.group("adminstrators");
//					.authorizeWith()
//					.role("ADMINISTRATOR");
	}
}
