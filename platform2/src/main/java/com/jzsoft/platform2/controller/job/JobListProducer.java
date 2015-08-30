package com.jzsoft.platform2.controller.job;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.JobRepository;
import com.jzsoft.platform2.model.Job;

@Model
public class JobListProducer {

	@Inject
	private JobRepository jobRepository;

	private List<Job> jobs;

	private List<Job> jobs_start;

	private String workTicketNumber;

	@Produces
	@Named
	public List<Job> getJobs() {
		return jobs;
	}

	@Produces
	@Named
	public List<Job> getJobs_start() {
		return jobs_start;
	}

	public void onJobListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Job job) {
		retrieveAllJobsOrderedByName();
	}

	@PostConstruct
	public void retrieveAllJobsOrderedByName() {
		jobs = jobRepository.findAllOrderedByTime();
		jobs_start = jobRepository.findAllOrderedByTimeTop(50);
	}

	public void retrieveAllJobsOrderedByWorkTicketNumber() {
		jobs = jobRepository.findByWorkTicketNumber(workTicketNumber);
	}

	public String getWorkTicketNumber() {
		return workTicketNumber;
	}

	public void setWorkTicketNumber(String workTicketNumber) {
		this.workTicketNumber = workTicketNumber;
	}

}
