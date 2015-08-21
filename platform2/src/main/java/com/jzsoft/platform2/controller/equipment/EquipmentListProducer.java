package com.jzsoft.platform2.controller.equipment;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jzsoft.platform2.data.EquipmentRepository;
import com.jzsoft.platform2.model.Equipment;

@Model
public class EquipmentListProducer {

	@Inject
	private EquipmentRepository equipmentRepository;

	private List<Equipment> equipments;

	@Produces
	@Named
	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void onEquipmentListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Equipment equipment) {
		retrieveAllEquipmentsOrderedByProductionDate();
	}

	@PostConstruct
	public void retrieveAllEquipmentsOrderedByProductionDate() {
		equipments = equipmentRepository.findAllOrderedByProductionDate();
	}

}
