package com.toparchy.molecule.workFlow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "JZSOFT_WORKFLOW_TASK")
@XmlRootElement
public class Task implements Serializable {

	private static final long serialVersionUID = 7932162757593864780L;

	@Id
	@Column(name = "TASK_ID", length = 50)
	@GeneratedValue(generator = "task-uuid")
	@GenericGenerator(name = "task-uuid", strategy = "uuid")
	private String id;

	private String subject;

	private String summary;

	private String status;

	private boolean isRelease;

	private boolean isOverdue;

	private boolean isCreator;

	private boolean isAcceptor;

	private boolean isApprover;

	private long startDate;

	private long endDate;

	private float completeRate;

	private long completeDate;

	private long totalWork;

	private long actualHours;

	private String priority;
	@Lob
	private String actTypeContent;

	private long planEndDate;

	private int delayDays;

	private int daysLeft;

	private String acceptorName;

	private String approvedName;

	private String creatorName;

	private String imagePath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isRelease() {
		return isRelease;
	}

	public void setRelease(boolean isRelease) {
		this.isRelease = isRelease;
	}

	public boolean isOverdue() {
		return isOverdue;
	}

	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}

	public boolean isCreator() {
		return isCreator;
	}

	public void setCreator(boolean isCreator) {
		this.isCreator = isCreator;
	}

	public boolean isAcceptor() {
		return isAcceptor;
	}

	public void setAcceptor(boolean isAcceptor) {
		this.isAcceptor = isAcceptor;
	}

	public boolean isApprover() {
		return isApprover;
	}

	public void setApprover(boolean isApprover) {
		this.isApprover = isApprover;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public float getCompleteRate() {
		return completeRate;
	}

	public void setCompleteRate(float completeRate) {
		this.completeRate = completeRate;
	}

	public long getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(long completeDate) {
		this.completeDate = completeDate;
	}

	public long getTotalWork() {
		return totalWork;
	}

	public void setTotalWork(long totalWork) {
		this.totalWork = totalWork;
	}

	public long getActualHours() {
		return actualHours;
	}

	public void setActualHours(long actualHours) {
		this.actualHours = actualHours;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getActTypeContent() {
		return actTypeContent;
	}

	public void setActTypeContent(String actTypeContent) {
		this.actTypeContent = actTypeContent;
	}

	public long getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(long planEndDate) {
		this.planEndDate = planEndDate;
	}

	public int getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(int delayDays) {
		this.delayDays = delayDays;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}

	public String getAcceptorName() {
		return acceptorName;
	}

	public void setAcceptorName(String acceptorName) {
		this.acceptorName = acceptorName;
	}

	public String getApprovedName() {
		return approvedName;
	}

	public void setApprovedName(String approvedName) {
		this.approvedName = approvedName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	// private int sort;
}
