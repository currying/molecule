package com.toparchy.molecule.rest;

import java.util.HashMap;
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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.toparchy.molecule.permission.data.MemberRepository;
import com.toparchy.molecule.permission.model.Member;
import com.toparchy.molecule.service.MemberRegistration;

@Path("/business/members")
@RequestScoped
public class MemberResourceRESTService {

	@Inject
	private Logger log;

	@Inject
	private Validator validator;

	@Inject
	private MemberRepository repository;

	@Inject
	MemberRegistration registration;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Member> listAllMembers() {
		return repository.findAllOrderedByName();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Member lookupMemberById(@PathParam("id") String id) {
		Member member = repository.findById(id);
		if (member == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return member;
	}

	/**
	 * Creates a new member from the values provided. Performs validation, and
	 * will return a JAX-RS response with either 200 ok, or with a map of
	 * fields, and related errors.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMember(Member member) {

		Response.ResponseBuilder builder = null;

		try {
			// validateMember(member);

			registration.register(member);

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

	// private void validateMember(Member member)
	// throws ConstraintViolationException, ValidationException {
	// // Create a bean validator and check for issues.
	// Set<ConstraintViolation<Member>> violations = validator
	// .validate(member);
	//
	// if (!violations.isEmpty()) {
	// throw new ConstraintViolationException(
	// new HashSet<ConstraintViolation<?>>(violations));
	// }
	//
	// // Check the uniqueness of the email address
	// if (emailAlreadyExists(member.getEmail())) {
	// throw new ValidationException("Unique Email Violation");
	// }
	// }

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

	// public boolean emailAlreadyExists(String email) {
	// Member member = null;
	// try {
	// member = repository.findByEmail(email);
	// } catch (NoResultException e) {
	// // ignore
	// }
	// return member != null;
	// }
}
