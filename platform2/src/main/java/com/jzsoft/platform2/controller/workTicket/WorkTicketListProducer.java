package com.jzsoft.platform2.controller.workTicket;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.WorkTicketRepository;
import com.jzsoft.platform2.model.WorkTicket;

@RequestScoped
public class WorkTicketListProducer {

	@Inject
	private WorkTicketRepository workTicketRepository;

	private List<WorkTicket> workTickets;

	private List<WorkTicket> newStartWorkTickets;

	@Produces
	@Named
	public List<WorkTicket> getWorkTickets() {
		return workTickets;
	}

	@Produces
	@Named
	public List<WorkTicket> getNewStartWorkTickets() {
		return newStartWorkTickets;
	}

	public void onWorkTicketListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final WorkTicket workTicket) {
		retrieveAllWorkTicketsOrderedByStartTime();
	}

	@PostConstruct
	public void retrieveAllWorkTicketsOrderedByStartTime() {
		workTickets = workTicketRepository.findAllOrderedByStartTime();
		newStartWorkTickets = workTicketRepository
				.findAllOrderedByStartTimeTop(50);
	}
}
