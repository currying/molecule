package com.toparchy.molecule.bi.shipBuilding.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;

import com.toparchy.molecule.bi.shipBuilding.data.HxjdjhRepository;

@Path("/business/hxjdjh")
public class HxjdjhRESTService {
	@Inject
	private HxjdjhRepository repository;

	@Path("/{gcbh}/{start}/{max}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	public Response getStorages(@PathParam("gcbh") String gcbh,
			@PathParam("start") int start, @PathParam("max") int max) {
		return Response.ok().entity(repository.findByGcbh(gcbh, start, max))
				.type(MediaType.APPLICATION_JSON).build();
	}
}
