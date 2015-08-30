package com.jzsoft.platform2.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.GZIP;

import com.jzsoft.platform2.data.JobRepository;
import com.jzsoft.platform2.model.Job;
import com.jzsoft.platform2.service.JobRegistration;

@Path("/job")
@RequestScoped
public class JobResourceRESTService {

	@Inject
	private Logger log;

	@Inject
	private Validator validator;

	@Inject
	private JobRepository repository;

	@Inject
	JobRegistration registration;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@GZIP
	public List<Job> listAllJobs() {
		return repository.findAllOrderedByTime();
	}

	@GET
	@Path("/get/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GZIP
	public Job lookupJobById(@PathParam("id") String id) {
		Job job = repository.findById(id);
		if (job == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return job;
	}

	@PUT
	@Path("/put/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GZIP
	public void updateWorkTicket(@PathParam("id") String id, Job job) {
		try {
			registration.update(job);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@GZIP
	public Response createJob(Job job) {
		Response.ResponseBuilder builder = null;
		try {
			validateJob(job);
			registration.register(job);
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("email", "Email taken");
			builder = Response.status(Response.Status.CONFLICT).entity(
					responseObj);
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(
					responseObj);
		}
		return builder.build();
	}

	private void validateJob(Job job) throws ConstraintViolationException,
			ValidationException {
		Set<ConstraintViolation<Job>> violations = validator.validate(job);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(
					new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	private Response.ResponseBuilder createViolationResponse(
			Set<ConstraintViolation<?>> violations) {
		log.fine("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(),
					violation.getMessage());
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}
}
