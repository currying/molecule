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
@Table(name = "JZ_BI_SHIPTYPE_")
@XmlRootElement
public class ShipType implements Serializable {

	private static final long serialVersionUID = 6635403051902837310L;
	@Id
	@Column(name = "SHIPTYPE_ID", length = 50)
	@GeneratedValue(generator = "shipType-uuid")
	@GenericGenerator(name = "shipType-uuid", strategy = "uuid")
	private String shipTypeId;
	@Column(name = "SHIPTYPE_NAME", length = 255)
	private String shipTypeName;
	@Column(name = "TOTAL_LENGTH", length = 50)
	private String totalLength; // 总长
	@Column(name = "LENGTH_BETWEEN_PERPENDICULARS", length = 50)
	private String lengthBetweenPerpendiculars;// 垂线间长
	@Column(name = "SHIP_BEAM", length = 50)
	private String shipBeam;// 船宽
	@Column(name = "MOULDED_DEPTH", length = 50)
	private String mouldedDepth;// 型深
	@Column(name = "DESIGNED_DRAFT", length = 50)
	private String designedDraft;// 设计吃水
	@Column(name = "SCANTLING_DRAFT", length = 50)
	private String scantlingDraft;// 结构吃水
	@Column(name = "DEAD_WEIGHT", length = 50)
	private String deadWeight;// 载重量
	@Column(name = "SAIL_ZONE", length = 50)
	private String sailZone;// 航区
	@Column(name = "PORT_OF_REGISTRY", length = 50)
	private String PortOfRegistry;// 港籍
	@Column(name = "FLAG", length = 50)
	private String flag;// 挂旗国
	@Column(name = "DESIGN_SPEED", length = 50)
	private String designSpeed;// 设计航速
	@Column(name = "CREW", length = 50)
	private String crew;// 船员
	@Column(name = "SHIP_CLASSIFICATION", length = 50)
	private String shipClassification;// 船级
	@Column(name = "PROFILE", length = 100)
	private String profile;// 侧面图
	@Column(name = "BOTTOM_PLATING", length = 100)
	private String bottomPlating;
	@Column(name = "DECK", length = 100)
	private String deck;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SHIPCLASS_ID")
	private ShipClass shipClass;
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "shipOwner")
	@JsonIgnore
	private Set<ShipProject> shipProjects;

	public ShipType() {
	}

	public String getShipTypeId() {
		return shipTypeId;
	}

	public void setShipTypeId(String shipTypeId) {
		this.shipTypeId = shipTypeId;
	}

	public String getShipTypeName() {
		return shipTypeName;
	}

	public void setShipTypeName(String shipTypeName) {
		this.shipTypeName = shipTypeName;
	}

	public String getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(String totalLength) {
		this.totalLength = totalLength;
	}

	public String getLengthBetweenPerpendiculars() {
		return lengthBetweenPerpendiculars;
	}

	public void setLengthBetweenPerpendiculars(
			String lengthBetweenPerpendiculars) {
		this.lengthBetweenPerpendiculars = lengthBetweenPerpendiculars;
	}

	public String getShipBeam() {
		return shipBeam;
	}

	public void setShipBeam(String shipBeam) {
		this.shipBeam = shipBeam;
	}

	public String getMouldedDepth() {
		return mouldedDepth;
	}

	public void setMouldedDepth(String mouldedDepth) {
		this.mouldedDepth = mouldedDepth;
	}

	public String getDesignedDraft() {
		return designedDraft;
	}

	public void setDesignedDraft(String designedDraft) {
		this.designedDraft = designedDraft;
	}

	public String getScantlingDraft() {
		return scantlingDraft;
	}

	public void setScantlingDraft(String scantlingDraft) {
		this.scantlingDraft = scantlingDraft;
	}

	public String getDeadWeight() {
		return deadWeight;
	}

	public void setDeadWeight(String deadWeight) {
		this.deadWeight = deadWeight;
	}

	public String getSailZone() {
		return sailZone;
	}

	public void setSailZone(String sailZone) {
		this.sailZone = sailZone;
	}

	public String getPortOfRegistry() {
		return PortOfRegistry;
	}

	public void setPortOfRegistry(String portOfRegistry) {
		PortOfRegistry = portOfRegistry;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDesignSpeed() {
		return designSpeed;
	}

	public void setDesignSpeed(String designSpeed) {
		this.designSpeed = designSpeed;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getShipClassification() {
		return shipClassification;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getBottomPlating() {
		return bottomPlating;
	}

	public void setBottomPlating(String bottomPlating) {
		this.bottomPlating = bottomPlating;
	}

	public String getDeck() {
		return deck;
	}

	public void setDeck(String deck) {
		this.deck = deck;
	}

	public void setShipClassification(String shipClassification) {
		this.shipClassification = shipClassification;
	}

	public ShipClass getShipClass() {
		return shipClass;
	}

	public void setShipClass(ShipClass shipClass) {
		this.shipClass = shipClass;
	}

	public Set<ShipProject> getShipProjects() {
		return shipProjects;
	}

	public void setShipProjects(Set<ShipProject> shipProjects) {
		this.shipProjects = shipProjects;
	}
}
