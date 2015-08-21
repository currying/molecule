package com.jzsoft.platform2.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@XmlRootElement
@Table(name = "WORKTICKET_", uniqueConstraints = @UniqueConstraint(columnNames = "WORKTICKETNUMBER_"))
public class WorkTicket implements Serializable {

	private static final long serialVersionUID = 5093469251387991438L;

	@Id
	@Column(name = "WORKTICKET_ID_", length = 50)
	@GeneratedValue(generator = "workticket-uuid")
	@GenericGenerator(name = "workticket-uuid", strategy = "uuid")
	private String id;

	@Column(name = "DESCRIPTION_")
	private String description;

	@Column(name = "STARTTIME_")
	private Long startTime;

	@Column(name = "ENDTIME_")
	private Long endTime;

	@Column(name = "STATE_")
	private int state;

	@NotNull(message = "请指定维修员")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID_")
	private Member member;

	@NotNull
	@Column(name = "WORKTICKETNUMBER_")
	private String workTicketNumber;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "WORKTICKET_MALFUNCTION_", joinColumns = @JoinColumn(name = "WORKTICKET_ID_"), inverseJoinColumns = @JoinColumn(name = "MALFUNCTION_ID_"))
	@JsonIgnore
	private Set<Malfunction> malfunctions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWorkTicketNumber() {
		return workTicketNumber;
	}

	public void setWorkTicketNumber(String workTicketNumber) {
		this.workTicketNumber = workTicketNumber;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Set<Malfunction> getMalfunctions() {
		return malfunctions;
	}

	public void setMalfunctions(Set<Malfunction> malfunctions) {
		this.malfunctions = malfunctions;
	}

}
