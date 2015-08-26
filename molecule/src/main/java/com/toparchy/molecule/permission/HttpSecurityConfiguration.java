package com.toparchy.molecule.permission;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

import javax.enterprise.event.Observes;

public class HttpSecurityConfiguration {

	public void onInit(@Observes SecurityConfigurationEvent event) {
		SecurityConfigurationBuilder builder = event.getBuilder();

		builder.http().forGroup("basic").authenticateWith().form()
				.authenticationUri("/login.jsf").loginPage("/login.jsf")
				.errorPage("/error.jsf").restoreOriginalRequest()
				.forPath("/logout").logout().redirectTo("/index.html")
				.forPath("/view/*", "basic").forPath("/run", "basic")
				.forPath("/frameset", "basic").forPath("/webcontent", "basic")
				.forPath("/report", "basic").forPath("/rest/business/*", "basic");
	}
}
