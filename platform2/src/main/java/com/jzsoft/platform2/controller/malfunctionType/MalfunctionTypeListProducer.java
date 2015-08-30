package com.jzsoft.platform2.controller.malfunctionType;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.MalfunctionTypeRepository;
import com.jzsoft.platform2.model.MalfunctionType;

@Model
public class MalfunctionTypeListProducer implements Serializable {

	private static final long serialVersionUID = -8651611899851585332L;

	@Inject
	private MalfunctionTypeRepository malfunctionTypeRepository;

	private List<MalfunctionType> malfunctionTypes;

	@Produces
	@Named
	public List<MalfunctionType> getMalfunctionTypes() {
		return malfunctionTypes;
	}

	public void onMalfunctionListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final MalfunctionType malfunctionType) {
		retrieveAllMalfunctionTypes();
	}

	@PostConstruct
	public void retrieveAllMalfunctionTypes() {
		malfunctionTypes = malfunctionTypeRepository.findAll();
	}

}
