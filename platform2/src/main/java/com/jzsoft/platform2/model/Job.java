package com.jzsoft.platform2.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "JOB_")
@XmlRootElement
public class Job implements Serializable {

	private static final long serialVersionUID = 6474548913731724088L;

	@Id
	@Column(name = "JOB_ID_", length = 50)
	private String id;

	@NotNull
	@Column(name = "WORKTICKETNUMBER_")
	private String workTicketNumber;

	@NotNull
	@Column(name = "JOBNAME_")
	private String jobName;

	// @Column(name = "STATE_")
	// private int state;

	@Column(name = "POSITION_")
	private String position;

	@Column(name = "JOBRECORD_")
	private String jobRecord;

	@Column(name = "STARTTIME_")
	private Long startTime;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "IMAGE_")
	private String image;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "AUDIO_")
	private String audio;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkTicketNumber() {
		return workTicketNumber;
	}

	public void setWorkTicketNumber(String workTicketNumber) {
		this.workTicketNumber = workTicketNumber;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	// public int getState() {
	// return state;
	// }
	//
	// public void setState(int state) {
	// this.state = state;
	// }

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getJobRecord() {
		return jobRecord;
	}

	public void setJobRecord(String jobRecord) {
		this.jobRecord = jobRecord;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
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

}
