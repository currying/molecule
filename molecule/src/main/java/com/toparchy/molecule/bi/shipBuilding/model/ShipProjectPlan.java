package com.toparchy.molecule.bi.shipBuilding.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "JZ_BI_SHIPPROJECTPLAN_")
@XmlRootElement
public class ShipProjectPlan implements Serializable {

	private static final long serialVersionUID = 5223131084909697254L;
	@Id
	@Column(name = "SHIPPROJECTPLAN_ID", length = 50)
	@GeneratedValue(generator = "shipProjectPlan-uuid")
	@GenericGenerator(name = "shipProjectPlan-uuid", strategy = "uuid")
	private String shipProjectPlanId;
	@Column(name = "SHIPPROJECT_PLAN_NAME", length = 100)
	private String shipProjectPlanName;
	@Column(name = "VERSION")
	private int version;
	@Column(name = "SORT")
	private int sort;
	@Column(name = "SCHEDULE", length = 100)
	private String schedule;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "shipProjectPlan")
	@JsonIgnore
	private Set<KanBan> kanban;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPPROJECT_ID")
	private ShipProject shipProject;

	public ShipProjectPlan() {
	}

	public String getShipProjectPlanId() {
		return shipProjectPlanId;
	}

	public void setShipProjectPlanId(String shipProjectPlanId) {
		this.shipProjectPlanId = shipProjectPlanId;
	}

	public String getShipProjectPlanName() {
		return shipProjectPlanName;
	}

	public void setShipProjectPlanName(String shipProjectPlanName) {
		this.shipProjectPlanName = shipProjectPlanName;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public Set<KanBan> getKanban() {
		return kanban;
	}

	public void setKanban(Set<KanBan> kanban) {
		this.kanban = kanban;
	}

	public ShipProject getShipProject() {
		return shipProject;
	}

	public void setShipProject(ShipProject shipProject) {
		this.shipProject = shipProject;
	}

}
