package com.jzsoft.platform2.controller.customer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.CustomerRepository;
import com.jzsoft.platform2.model.Customer;

@Model
public class CustomerListProducer {

	@Inject
	private CustomerRepository customerRepository;

	private List<Customer> customers;

	@Produces
	@Named
	public List<Customer> getCustomers() {
		return customers;
	}

	public void onCustomerListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Customer customer) {
		retrieveAllCustomersOrderedByName();
	}

	@PostConstruct
	public void retrieveAllCustomersOrderedByName() {
		customers = customerRepository.findAllOrderedByName();
	}

}
