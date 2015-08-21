package com.toparchy.molecule.bi.shipBuilding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "JZ_BI_KANBAN_")
@XmlRootElement
public class KanBan implements Serializable {

	private static final long serialVersionUID = 7358437827224463040L;
	@Id
	@Column(name = "KANBAN_ID", length = 50)
	@GeneratedValue(generator = "kanban-uuid")
	@GenericGenerator(name = "kanban-uuid", strategy = "uuid")
	private String kanbanId;
	@Column(name = "KANBAN_NAME", length = 50)
	private String kanbanName;
	@Column(name = "KANBAN", length = 50)
	private String kanban;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPPROJECTPLAN_ID")
	private ShipProjectPlan shipProjectPlan;

	public String getKanbanId() {
		return kanbanId;
	}

	public void setKanbanId(String kanbanId) {
		this.kanbanId = kanbanId;
	}

	public String getKanban() {
		return kanban;
	}

	public void setKanban(String kanban) {
		this.kanban = kanban;
	}

	public String getKanbanName() {
		return kanbanName;
	}

	public void setKanbanName(String kanbanName) {
		this.kanbanName = kanbanName;
	}

	public ShipProjectPlan getShipProjectPlan() {
		return shipProjectPlan;
	}

	public void setShipProjectPlan(ShipProjectPlan shipProjectPlan) {
		this.shipProjectPlan = shipProjectPlan;
	}

}
