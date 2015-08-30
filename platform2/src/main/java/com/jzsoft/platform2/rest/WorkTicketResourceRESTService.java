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

import com.jzsoft.platform2.data.WorkTicketRepository;
import com.jzsoft.platform2.model.WorkTicket;
//import com.jzsoft.platform2.rbac.MaintenanceMan;
import com.jzsoft.platform2.service.WorkTicketRegistration;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 */
@Path("/workticket")
@RequestScoped
public class WorkTicketResourceRESTService {

	@Inject
	private Logger log;

	@Inject
	private Validator validator;

	@Inject
	private WorkTicketRepository repository;

	@Inject
	WorkTicketRegistration registration;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@GZIP
	public List<WorkTicket> listAllWorkTickets() {
		return repository.findAllOrderedByStartTime();
	}

	@GET
	@Path("/get/workTicketNumber/{workTicketNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public WorkTicket lookupWorkTicketByWorkTicketNumber(
			@PathParam("workTicketNumber") String workTicketNumber) {
		WorkTicket workTicket = repository
				.findByWorkTicketNumber(workTicketNumber);
		return workTicket;
	}

	@GET
	@Path("/get/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public WorkTicket lookupWorkTicketById(@PathParam("id") String id) {
		WorkTicket workTicket = repository.findById(id);
		if (workTicket == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return workTicket;
	}

	@GET
	@Path("/get/member/{phoneNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	@GZIP
	// @MaintenanceMan
	public List<WorkTicket> lookupWorkTicketByMember(
			@PathParam("phoneNumber") String phoneNumber) {
		List<WorkTicket> workTickets = repository
				.findAllOrderedByPhoneNumber(phoneNumber);
		if (workTickets.size() <= 0) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return workTickets;
	}

	@PUT
	@Path("/put/workTicketNumber/{workTicketNumber:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateWorkTicket(
			@PathParam("workTicketNumber") String workTicketNumber,
			WorkTicket workTicket) {
		try {
			registration.update(workTicket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new member from the values provided. Performs validation, and
	 * will return a JAX-RS response with either 200 ok, or with a map of
	 * fields, and related errors.
	 */
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWorkTicket(WorkTicket workTicket) {

		Response.ResponseBuilder builder = null;

		try {
			// Validates member using bean validation
			validateWorkTicket(workTicket);

			registration.register(workTicket);

			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("email", "Email taken");
			builder = Response.status(Response.Status.CONFLICT).entity(
					responseObj);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(
					responseObj);
		}

		return builder.build();
	}

	private void validateWorkTicket(WorkTicket workTicket)
			throws ConstraintViolationException, ValidationException {
		Set<ConstraintViolation<WorkTicket>> violations = validator
				.validate(workTicket);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(
					new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	/**
	 * Creates a JAX-RS "Bad Request" response including a map of all violation
	 * fields, and their message. This can then be used by clients to show
	 * violations.
	 * 
	 * @param violations
	 *            A set of violations that needs to be reported
	 * @return JAX-RS response containing all violations
	 */
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
