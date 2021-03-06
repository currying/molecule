package com.toparchy.molecule.scxt.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;

import com.toparchy.molecule.permission.annotations.P00000008;
import com.toparchy.molecule.scxt.data.WorkingHoursVolumeRepository;

@Path("/business/workingHoursVolume")
public class WorkingHoursVolumeResourceRESTService {
	@Inject
	private WorkingHoursVolumeRepository repository;

	@Path("/{start}/{max}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	@P00000008
	public Response getWorkingHoursVolumes(@PathParam("start") int start, @PathParam("max") int max) {
		return Response.ok().entity(repository.findAll(start, max)).type(MediaType.APPLICATION_JSON).build();
	}

	@Path("/condition")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GZIP
	@P00000008
	public Response getWorkingHoursVolumesBypgdbh(SelectHandler selectHandler) {
		return Response.ok().entity(repository.findByCondition(selectHandler.getName(), selectHandler.getValue(),
				selectHandler.getStart(), selectHandler.getMax())).type(MediaType.APPLICATION_JSON).build();
	}

}
