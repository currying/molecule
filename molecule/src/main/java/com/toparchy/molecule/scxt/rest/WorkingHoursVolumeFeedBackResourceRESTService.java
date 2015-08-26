package com.toparchy.molecule.scxt.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;

import com.toparchy.molecule.permission.annotations.*;
import com.toparchy.molecule.scxt.data.WorkingHoursVolumeFeedBackRepository;

@Path("/business/workingHoursVolumeFeedBack")
public class WorkingHoursVolumeFeedBackResourceRESTService {
	@Inject
	private WorkingHoursVolumeFeedBackRepository repository;

	@Path("/{id}/{start}/{max}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	@P7
	public Response getWorkingHoursVolumeFeedBacks(@PathParam("id") String id, @PathParam("start") int start,
			@PathParam("max") int max) {
		return Response.ok().entity(repository.findById(id, start, max)).type(MediaType.APPLICATION_JSON).build();
	}

}
