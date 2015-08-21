package com.jzsoft.platform2.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MALFUNCTION_")
@XmlRootElement
public class Malfunction implements Serializable {

	private static final long serialVersionUID = -8752071234821063888L;

	@Id
	@Column(name = "MALFUNCTION_ID_", length = 50)
	@GeneratedValue(generator = "malfunction-uuid")
	@GenericGenerator(name = "malfunction-uuid", strategy = "uuid")
	private String id;

	@NotNull
	@Column(name = "MALFUNCTIONRECORD_")
	private String malfunctionRecord;

	@Column(name = "TIME_")
	private Long time;

	@Column(name = "IMPORTANCE_")
	private int importance;

	@NotNull(message = "请指定客户")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_ID_")
	private Customer customer;

	@Column(name = "DISPATCHINGMARK_")
	private int dispatchingMark;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "IMAGE_")
	private String image;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "AUDIO_")
	private String audio;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "WORKTICKET_ID_")
	private WorkTicket workTicket;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "MALFUNCTIONTYPE_ID_")
	private MalfunctionType malfunctionType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMalfunctionRecord() {
		return malfunctionRecord;
	}

	public void setMalfunctionRecord(String malfunctionRecord) {
		this.malfunctionRecord = malfunctionRecord;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getDispatchingMark() {
		return dispatchingMark;
	}

	public void setDispatchingMark(int dispatchingMark) {
		this.dispatchingMark = dispatchingMark;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public WorkTicket getWorkTicket() {
		return workTicket;
	}

	public void setWorkTicket(WorkTicket workTicket) {
		this.workTicket = workTicket;
	}

	public MalfunctionType getMalfunctionType() {
		return malfunctionType;
	}

	public void setMalfunctionType(MalfunctionType malfunctionType) {
		this.malfunctionType = malfunctionType;
	}

}
