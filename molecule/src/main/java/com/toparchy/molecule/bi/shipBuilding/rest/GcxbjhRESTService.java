package com.toparchy.molecule.bi.shipBuilding.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;

import com.toparchy.molecule.bi.shipBuilding.data.GcxbjhRepository;

@Path("/business/gcxbjh")
public class GcxbjhRESTService {
	@Inject
	private GcxbjhRepository repository;

	@Path("/{gcbh}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	public Response getStorages(@PathParam("gcbh") String gcbh) {
		return Response.ok().entity(repository.findByGcbh(gcbh))
				.type(MediaType.APPLICATION_JSON).build();
	}
}
