package com.jzsoft.platform2.controller.malfunction;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.MalfunctionRepository;
import com.jzsoft.platform2.model.Malfunction;

@Model
public class MalfunctionListProducer implements Serializable {

	private static final long serialVersionUID = 5450716758076288196L;

	@Inject
	private MalfunctionRepository malfunctionRepository;

	private List<Malfunction> malfunctions;

	@Produces
	@Named
	public List<Malfunction> getMalfunctions() {
		return malfunctions;
	}

	public void onMalfunctionListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Malfunction malfunction) {
		retrieveAllMalfunctionsOrderedByTime();
	}

	@PostConstruct
	public void retrieveAllMalfunctionsOrderedByTime() {
		malfunctions = malfunctionRepository.findAllOrderedByTime();
	}

	public void retrieveAllMalfunctionsByCustomer(String customer) {
		malfunctions = malfunctionRepository.findByCustomer(customer);
	}

}
