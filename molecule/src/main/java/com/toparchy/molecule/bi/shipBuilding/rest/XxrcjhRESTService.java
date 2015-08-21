package com.toparchy.molecule.bi.shipBuilding.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;

import com.toparchy.molecule.bi.shipBuilding.data.XxrcjhRepository;

@Path("/business/xxrcjh")
public class XxrcjhRESTService {
	@Inject
	private XxrcjhRepository repository;

	@Path("/fd/{gcbh}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	public Response getFd(@PathParam("gcbh") String gcbh) {
		return Response.ok().entity(repository.findFdByGdbh(gcbh))
				.type(MediaType.APPLICATION_JSON).build();
	}

	@Path("/zz/{gcbh}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	public Response getZz(@PathParam("gcbh") String gcbh) {
		return Response.ok().entity(repository.findZzByGdbh(gcbh))
				.type(MediaType.APPLICATION_JSON).build();
	}
}
