package com.jzsoft.platform2.controller.equipmentType;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.EquipmentTypeRepository;
import com.jzsoft.platform2.model.EquipmentType;

@Model
public class EquipmentTypeListProducer {

	@Inject
	private EquipmentTypeRepository equipmentTypeRepository;

	private List<EquipmentType> equipmentTypes;

	@Produces
	@Named
	public List<EquipmentType> getEquipmentTypes() {
		return equipmentTypes;
	}

	public void onEquipmentTypeListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final EquipmentType equipmentType) {
		retrieveAllEquipmentTypesOrderedByName();
	}

	@PostConstruct
	public void retrieveAllEquipmentTypesOrderedByName() {
		equipmentTypes = equipmentTypeRepository.findAllOrderedByName();
	}

}
