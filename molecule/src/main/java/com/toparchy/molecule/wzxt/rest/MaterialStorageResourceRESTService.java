package com.toparchy.molecule.wzxt.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.spi.UnhandledException;
import org.picketlink.idm.IdentityManagementException;

import com.toparchy.molecule.permission.annotations.P00000001;
import com.toparchy.molecule.permission.annotations.P00000002;
import com.toparchy.molecule.permission.annotations.P00000003;
import com.toparchy.molecule.permission.annotations.P00000004;
import com.toparchy.molecule.permission.annotations.P00000005;
import com.toparchy.molecule.permission.annotations.P00000006;
import com.toparchy.molecule.wzxt.data.MaterialStorageRepository;
//import com.toparchy.molecule.wzxt.model.MaterialStorageFilter;
import com.toparchy.molecule.wzxt.model.MaterialStorage;

@Path("/business/materialStorage")
public class MaterialStorageResourceRESTService {
	@Inject
	private Logger log;
	@Inject
	private MaterialStorageRepository repository;

	@Path("/all/{ldbh}/{start}/{max}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	@P00000001
	public Response getMaterialStorages(@PathParam("ldbh") String ldbh, @PathParam("start") int start,
			@PathParam("max") int max) {
		return Response.ok().entity(repository.findByLdbh(ldbh, start, max, 2)).type(MediaType.APPLICATION_JSON)
				.build();
	}

	@Path("/nomark/{ldbh}/{start}/{max}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	@P00000002
	public Response getMaterialStoragesNoMark(@PathParam("ldbh") String ldbh, @PathParam("start") int start,
			@PathParam("max") int max) {
		return Response.ok().entity(repository.findByLdbh(ldbh, start, max, 0)).type(MediaType.APPLICATION_JSON)
				.build();
	}

	@Path("/mark/{ldbh}/{start}/{max}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@GZIP
	@P00000003
	public Response getMaterialStoragesMark(@PathParam("ldbh") String ldbh, @PathParam("start") int start,
			@PathParam("max") int max) {
		return Response.ok().entity(repository.findByLdbh(ldbh, start, max, 1)).type(MediaType.APPLICATION_JSON)
				.build();
	}

	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@P00000004
	public Response createWzrkd(MaterialStorage materialStorage) {
		Response.ResponseBuilder builder = null;
		builder = Response.serverError();
		return builder.type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@P00000005
	public Response updateWzrkd(List<MaterialStorage> materialStorages) {
		Response.ResponseBuilder builder = null;
		try {
			repository.updateMaterialStorage(materialStorages);
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.type(MediaType.APPLICATION_JSON_TYPE).build();
	}

	@Path("/delete/{wzrkdId}")
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
	@P00000006
	public Response deleteWzrkd(@PathParam("wzrkdId") long wzrkdId) {
		Response.ResponseBuilder builder = null;
		try {
			repository.delteteMaterialStorage(wzrkdId);
			builder = Response.ok();
		} catch (UnhandledException e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		} catch (IdentityManagementException e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();
	}

	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		log.fine("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}
}
