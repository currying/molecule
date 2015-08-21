//package com.toparchy.molecule.bi.shipBuilding.model;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;
//
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//@Table(name = "JZ_BI_SHIPPROJECTTIME_")
//@XmlRootElement
//public class ShipProjectTime implements Serializable {
//	private static final long serialVersionUID = 3919978484518029778L;
//	@Id
//	@Column(name = "SHIPPROJECTTIME_ID", length = 50)
//	@GeneratedValue(generator = "shipProjectTime-uuid")
//	@GenericGenerator(name = "shipProjectTime-uuid", strategy = "uuid")
//	private String shipProjectTimeId;
//	@Column(name = "DELIVERY_DATE", length = 50)
//	private String deliveryDate;// 客户规定交货日期
//	@Column(name = "PLANNED_START_DATE", length = 50)
//	private String plannedStartDate;// 计划开工
//	@Column(name = "ACTUAL_START_DATE", length = 50)
//	private String actualStartDate;// 实际开工
//	@Column(name = "PLANNED_DOCKING", length = 50)
//	private String plannedDocking;// 计划进坞
//	@Column(name = "ACTUAL_DOCKING", length = 50)
//	private String actualDocking;// 实际进坞
//	@Column(name = "PLANNED_UNDOCKING", length = 50)
//	private String plannedUndocking;// 计划出坞
//	@Column(name = "ACTUAL_UNDOCKING", length = 50)
//	private String actualUndocking;// 实际出坞
//	@Column(name = "PLANNED_TRIAL_TRIP", length = 50)
//	private String plannedTrialTrip;// 计划试航
//	@Column(name = "ACTUAL_TRIAL_TRIP", length = 50)
//	private String actualTrialTrip;// 实际试航
//	@Column(name = "PLANNED_DELIVERY_OF_VESSEL", length = 50)
//	private String plannedDeliveryOfVessel;// 计划交船
//	@Column(name = "ACTUAL_DELIVERY_OF_VESSEL", length = 50)
//	private String actualDeliveryOfVessel;// 实际交船
//	@Column(name = "VERSION", length = 50)
//	private int version;// 版本
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "SHIPPROJECT_ID")
//	private ShipProject shipProject;
//
//	public ShipProjectTime() {
//	}
//
//	public String getDeliveryDate() {
//		return deliveryDate;
//	}
//
//	public void setDeliveryDate(String deliveryDate) {
//		this.deliveryDate = deliveryDate;
//	}
//
//	public String getPlannedStartDate() {
//		return plannedStartDate;
//	}
//
//	public void setPlannedStartDate(String plannedStartDate) {
//		this.plannedStartDate = plannedStartDate;
//	}
//
//	public String getActualStartDate() {
//		return actualStartDate;
//	}
//
//	public void setActualStartDate(String actualStartDate) {
//		this.actualStartDate = actualStartDate;
//	}
//
//	public String getPlannedDocking() {
//		return plannedDocking;
//	}
//
//	public void setPlannedDocking(String plannedDocking) {
//		this.plannedDocking = plannedDocking;
//	}
//
//	public String getActualDocking() {
//		return actualDocking;
//	}
//
//	public void setActualDocking(String actualDocking) {
//		this.actualDocking = actualDocking;
//	}
//
//	public String getPlannedUndocking() {
//		return plannedUndocking;
//	}
//
//	public void setPlannedUndocking(String plannedUndocking) {
//		this.plannedUndocking = plannedUndocking;
//	}
//
//	public String getActualUndocking() {
//		return actualUndocking;
//	}
//
//	public void setActualUndocking(String actualUndocking) {
//		this.actualUndocking = actualUndocking;
//	}
//
//	public String getPlannedTrialTrip() {
//		return plannedTrialTrip;
//	}
//
//	public void setPlannedTrialTrip(String plannedTrialTrip) {
//		this.plannedTrialTrip = plannedTrialTrip;
//	}
//
//	public String getActualTrialTrip() {
//		return actualTrialTrip;
//	}
//
//	public void setActualTrialTrip(String actualTrialTrip) {
//		this.actualTrialTrip = actualTrialTrip;
//	}
//
//	public String getPlannedDeliveryOfVessel() {
//		return plannedDeliveryOfVessel;
//	}
//
//	public void setPlannedDeliveryOfVessel(String plannedDeliveryOfVessel) {
//		this.plannedDeliveryOfVessel = plannedDeliveryOfVessel;
//	}
//
//	public String getActualDeliveryOfVessel() {
//		return actualDeliveryOfVessel;
//	}
//
//	public void setActualDeliveryOfVessel(String actualDeliveryOfVessel) {
//		this.actualDeliveryOfVessel = actualDeliveryOfVessel;
//	}
//
//	public int getVersion() {
//		return version;
//	}
//
//	public void setVersion(int version) {
//		this.version = version;
//	}
//
//	public ShipProject getShipProject() {
//		return shipProject;
//	}
//
//	public void setShipProject(ShipProject shipProject) {
//		this.shipProject = shipProject;
//	}
//
//}
